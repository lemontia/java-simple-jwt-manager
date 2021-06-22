package com.example.sample.controller.dto;

import lombok.Getter;

@Getter
public class ResponseJwtInfo {
    private String username;

    public ResponseJwtInfo(String username) {
        this.username = username;
    }
}
