package academiaapi.ipd.gob.pe.academiaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiexternoConfig {
    //para crear y inyectar en nuestro controlador(HTTP reutilizable dentro de Spring Boot)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
