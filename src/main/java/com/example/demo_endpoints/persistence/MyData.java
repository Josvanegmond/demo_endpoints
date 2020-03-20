package com.example.demo_endpoints.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MyData {

    private static int userCount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private int userNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_id")
    private List<MyChildData> child = new ArrayList<>();

    public MyData() {}

    public MyData(String username) {

        userCount++;

        this.username = username;
        this.userNumber = userCount;
    }

    public String getUsername() {
        return username;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public List<MyChildData> getChild() {
        return this.child;
    }

    public void addChild(String content) {
        this.child.add(new MyChildData(content));
    }
}
