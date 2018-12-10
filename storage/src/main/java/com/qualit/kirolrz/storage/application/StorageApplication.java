package com.qualit.kirolrz.storage.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.qualit.kirolrz.storage")
@EntityScan("com.qualit.kirolrz.storage")
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.qualit.kirolrz.storage.repository"})
public class StorageApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(StorageApplication.class, args);

        /*for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }*/
    }
}
