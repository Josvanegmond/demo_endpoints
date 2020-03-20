package com.example.demo_endpoints.controller;

import com.example.demo_endpoints.persistence.MyData;
import com.example.demo_endpoints.persistence.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api1")
public class EndpointController {

    @Autowired
    private MyDataRepository myDataRepository;

    @GetMapping("/{username}")
    public MyData showMyData(@PathVariable String username) {

        MyData myData = myDataRepository.findByUsername(username);

        if(myData == null) {
            myData = new MyData(username);
            myData.addChild("I am a child!");
            myDataRepository.save(myData);
        }

        return myData;
    }

    @GetMapping("/")
    public List<MyData> showAllData() {
        return myDataRepository.findAll();
    }

}
