package ru.nefedova.nexign;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Тестовое задание",
                version = "1.0.0",
                contact = @Contact(
                        name = "Екатерина Нефедова",
                        email = "chugunovaee@mail.ru"
                )
        )
)
@SpringBootApplication
public class NexignApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexignApplication.class, args);
    }

}
