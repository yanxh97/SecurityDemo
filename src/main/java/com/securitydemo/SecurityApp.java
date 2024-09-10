package com.securitydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SecurityApp {
    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(SecurityApp.class, args);
        System.out.println(run);
    }
}
