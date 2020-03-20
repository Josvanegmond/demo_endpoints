package com.example.demo_endpoints.controller;

import com.example.demo_endpoints.persistence.MyData;
import com.example.demo_endpoints.persistence.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api3")
public class EndpointController3 {

    @Autowired
    private MyDataRepository myDataRepository;

    @PostMapping(value = "/create/{username}")
    public ResponseEntity<MyData> createMyData(HttpServletRequest request, @PathVariable String username) {

        if(myDataRepository.findByUsername(username) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        MyData myData = new MyData(username);

        String childContent = request.getParameter("content");
        if(childContent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(childContent.length() > 50) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build();
        }
        if(childContent.equals("teapot")) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }

        myData.addChild(childContent);

        myDataRepository.save(myData);

        return ResponseEntity.status(HttpStatus.CREATED).body(myData);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<MyData>> getAllData() {
        List<MyData> dataList = myDataRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dataList);
    }

}
