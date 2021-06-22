package com.example.sample.controller;

import com.example.sample.config.jwt.JwtManager;
import com.example.sample.controller.dto.ResponseJwtCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final JwtManager jwtManager;

    /**
     * JWT 발급
     */
    @GetMapping("/api/jwt/create/{username}")
    public ResponseJwtCreate jwtCreate(@PathVariable String username) {
        System.out.println("username = " + username);
        String token = jwtManager.generateToken(username);

        return new ResponseJwtCreate(token);
    }

    /**
     * JWT 토큰 해석
     * @return
     */
    @GetMapping("/api/jwt/info/{token}")
    public JwtManager.TokenInfo jwtInfo(@PathVariable String token) {
        System.out.println("token = " + token);
        JwtManager.TokenInfo tokenInfo = jwtManager.getTokenInfo(token);

        return tokenInfo;
    }
}
