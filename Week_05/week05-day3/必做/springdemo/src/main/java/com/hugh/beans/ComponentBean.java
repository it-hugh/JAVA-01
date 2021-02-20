package com.hugh.beans;

import org.springframework.stereotype.Component;

@Component
public class ComponentBean {
    private Long id;
    private String name = "bean-@Component";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
