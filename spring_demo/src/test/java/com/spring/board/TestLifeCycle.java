package com.spring.board;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll(){
        System.out.println("TEST==========beforeAll==========+\n");

    }

    @AfterAll
    static void afterAll(){
        System.out.println("TEST==========afterAll==========");

    }

    @BeforeEach
    void beforeEach(){
        System.out.println("TEST==========beforeEach==========");

    }

    @AfterEach
    void afterEach(){
        System.out.println("TEST==========afterEach==========");

    }



}
