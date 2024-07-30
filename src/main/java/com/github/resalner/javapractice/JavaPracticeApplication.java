package com.github.resalner.javapractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.github.resalner.javapractice.repository")
public class JavaPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaPracticeApplication.class, args);
    }
}
