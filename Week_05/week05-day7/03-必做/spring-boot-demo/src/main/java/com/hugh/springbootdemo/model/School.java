package com.hugh.springbootdemo.model;

import lombok.Data;

@Data
public class School {

    Klass class1;

    Student student100;

    public void ding() {

        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);

    }
}
