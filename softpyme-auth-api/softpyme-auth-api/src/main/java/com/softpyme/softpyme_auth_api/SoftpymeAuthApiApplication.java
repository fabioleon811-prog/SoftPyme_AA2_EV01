
package com.softpyme.softpyme_auth_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Permite que Spring detecte los controladores y servicios de SoftPyme.
@SpringBootApplication(scanBasePackages = "com.softpyme")
public class SoftpymeAuthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftpymeAuthApiApplication.class, args);
    }
}
