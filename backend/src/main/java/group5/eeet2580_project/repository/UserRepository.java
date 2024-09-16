package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserDetailsService {

    @Query("SELECT U FROM User U WHERE U.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT U FROM User U WHERE U.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT U FROM User U WHERE U.username = :username OR U.email = :email")
    Optional<User> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(U) > 0 THEN true ELSE false END FROM User U WHERE U.username = :username")
    Boolean existsByUsername(@Param("username") String username);

    @Query("SELECT CASE WHEN COUNT(U) > 0 THEN true ELSE false END FROM User U WHERE U.email = :email")
    Boolean existsByEmail(@Param("email") String email);

    default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Query("SELECT U FROM User U WHERE U.fullName = :fullName")
    Optional<User> findByFullName(@Param("fullName") String fullName);

    @Query("SELECT U FROM User U WHERE :role MEMBER OF U.roles")
    List<User> findAllByRoles(String role);
}
