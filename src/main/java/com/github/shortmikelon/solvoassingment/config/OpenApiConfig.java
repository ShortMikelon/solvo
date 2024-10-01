package com.github.shortmikelon.solvoassingment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI beanOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Aset Kenes");
        myContact.setEmail("kenesaset485@gmail.com");

        Info information = new Info()
                .title("Solvo Testing Assignment API")
                .version("1.0")
                .description("This API exposes endpoints to manage transactions and limits.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
