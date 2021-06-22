package com.example.sample;

import org.apache.logging.log4j.util.Base64Util;

public class TestClass {
    public static void main(String[] args) {
        String secretKey = "test-jwt-sample-springboot";
        String encode = Base64Util.encode(secretKey);
        System.out.println("encode = " + encode);
    }
}
