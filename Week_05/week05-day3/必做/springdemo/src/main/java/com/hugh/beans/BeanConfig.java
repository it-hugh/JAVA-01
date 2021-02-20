package com.hugh.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ConfigBean getBean() {
        ConfigBean bean = new ConfigBean();
        bean.setId(2L);
        bean.setName("bean-@Configuration");
        return bean;
    }

}
