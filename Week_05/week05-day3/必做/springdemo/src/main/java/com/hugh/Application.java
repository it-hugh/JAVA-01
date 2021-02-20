package com.hugh;

import com.hugh.beans.BeanPrinter;
import com.hugh.beans.XmlBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        XmlBean xmlBean = (XmlBean) context.getBean("xmlBean");
        System.out.println(xmlBean.getName());
        BeanPrinter printOther = (BeanPrinter) context.getBean("beanPrinter");
        printOther.printBeans();
    }


}
