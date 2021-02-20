package com.hugh.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanPrinter {

    @Autowired
    ComponentBean componentBean;

    @Autowired
    ConfigBean configBean;

    public void printBeans() {
        System.out.println(componentBean.getName());
        System.out.println(configBean.getName());
    }

}
