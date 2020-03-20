package com.example.demo_endpoints.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyChildData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    public MyChildData() {}

    public MyChildData(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
