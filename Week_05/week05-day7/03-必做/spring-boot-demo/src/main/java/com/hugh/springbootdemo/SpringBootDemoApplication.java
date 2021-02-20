package com.hugh.springbootdemo;

import com.hugh.springbootdemo.model.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringBootDemoApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        for (String str : beanNames) {
            //打印上下文中的beanName
            System.out.println(str);
        }

    }

    @Bean
    @ConditionalOnClass(Teacher.class)
    public Teacher getTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("王强");
        teacher.setSubjcet("数学");
        return teacher;
    }

}
