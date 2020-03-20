package com.example.demo_endpoints.controller;

import com.example.demo_endpoints.persistence.MyData;
import com.example.demo_endpoints.persistence.MyDataRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api2")
public class EndpointController2 {

    @Autowired
    private MyDataRepository myDataRepository;

    @GetMapping(value = "/create/{username}")
    public ResponseEntity<MyData> createMyData(@PathVariable String username) {

        if(myDataRepository.findByUsername(username) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        MyData myData = new MyData(username);
        myDataRepository.save(myData);

        return ResponseEntity.ok(myData);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<MyData> showMyData(@PathVariable String username) {

        MyData myData = myDataRepository.findByUsername(username);

        if(myData == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(myData);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<MyData>> showAllData() {
        return ResponseEntity.ok(myDataRepository.findAll());
    }

}
