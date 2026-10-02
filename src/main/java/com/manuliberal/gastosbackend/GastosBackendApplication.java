package com.manuliberal.gastosbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class GastosBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GastosBackendApplication.class, args);
        System.out.println("arranca la app!");
    }

}
