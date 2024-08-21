package group5.eeet2580_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomBasicAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String serviceUsername;
    private final String servicePassword;

    public CustomBasicAuthFilter(AuthenticationManager authenticationManager, @Value("${service.auth.username}") String serviceUsername, @Value("${service.auth.password}") String servicePassword) {
        setAuthenticationManager(authenticationManager);
        this.serviceUsername = serviceUsername;
        this.servicePassword = servicePassword;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String[] credentials = decodeBasicAuth(authHeader);
            if (credentials.length == 2) {
                String username = credentials[0];
                String password = credentials[1];
                if (serviceUsername.equals(username) && servicePassword.equals(password)) {
                    return new User(
                            username, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_SERVICE")));
                }
            }
        }
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }

    private String[] decodeBasicAuth(String authHeader) {
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(authHeader.substring(6));
        return new String(decodedBytes).split(":", 2);
    }
}
