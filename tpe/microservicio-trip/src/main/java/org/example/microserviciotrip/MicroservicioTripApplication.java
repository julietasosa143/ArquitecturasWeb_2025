package org.example.microserviciotrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
public class MicroservicioTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioTripApplication.class, args);
    }
    //URL SWAGGER http://localhost:8081/swagger-ui/index.html
}
