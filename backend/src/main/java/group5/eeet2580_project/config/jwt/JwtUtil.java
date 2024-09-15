package group5.eeet2580_project.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final long cacheExpiry;
    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret.key}") String jwtSecret,
                   @Value("${jwt.cache.expiry}") long cacheExpiry) {
        this.cacheExpiry = cacheExpiry;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Set<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", Set.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateAccessToken(String username, Set<String> roles) {
        return generateToken(username, roles, cacheExpiry * 1000);
    }

    public String generateRefreshToken(String username, Set<String> roles) {
        return generateToken(username, roles, cacheExpiry * 1000 * 2);
    }

    private String generateToken(String username, Set<String> roles, long expiry) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(secretKey)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        if (userDetails == null) {
            return !isTokenExpired(token);
        }
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
