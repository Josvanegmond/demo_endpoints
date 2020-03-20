package com.example.demo_endpoints.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyDataRepository extends JpaRepository<MyData, Long> {

    MyData findByUserNumber(int number);
    MyData findByUsername(String username);

}
