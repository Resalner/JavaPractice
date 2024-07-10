package com.github.resalner.javapractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JavaPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaPracticeApplication.class, args);
    }
}
