package com.example.sample.controller.dto;

import lombok.Getter;

@Getter
public class ResponseJwtCreate {
    private String token;

    public ResponseJwtCreate(String token) {
        this.token = token;
    }
}
