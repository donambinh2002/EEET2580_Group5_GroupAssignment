package group5.eeet2580_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/auth/**") // Adjust the endpoint pattern as needed
                .allowedOrigins("http://localhost:5173") // Your frontend's URL
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
