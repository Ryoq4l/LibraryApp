package org.tisi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.tisi.model")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
