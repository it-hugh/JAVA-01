package com.hugh.springbootdemo.config;

import com.hugh.springbootdemo.model.Klass;
import com.hugh.springbootdemo.model.School;
import com.hugh.springbootdemo.model.Student;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class ModelConfig {
    @Bean
    public Student getStudent() {
        return new Student(1,"hugh");
    }

    @Bean
    public Klass getKlass() {
        return new Klass();
    }

    @Bean
    public School getSchool() {
        return new School();
    }
}
