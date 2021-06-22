package com.example.sample.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Set;

public class JwtManager {
    private String secretKey;
    private long TOKEN_VALIDATiON_SECOND = 60;

    public JwtManager(String secretKey, long TOKEN_VALIDATiON_SECOND) {
        this.secretKey = secretKey;
        this.TOKEN_VALIDATiON_SECOND = TOKEN_VALIDATiON_SECOND;
    }

    // secretKey 로드
    private Key getSigninKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰 생성
    public String generateToken(String username) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (TOKEN_VALIDATiON_SECOND * 1000)))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 정보 리턴
    public TokenInfo getTokenInfo(String token) {
        Claims body = getClaims(token);
        Set<String> keySet = body.keySet();
        for (String s : keySet) {
            System.out.println("s = " + s);
        }

        String username = body.get("username", String.class);
        Date issuedAt = body.getIssuedAt();
        Date expiration = body.getExpiration();
        return new TokenInfo(username, issuedAt, expiration);
    }

    // 토큰정보 해석
    private Claims getClaims(String token) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body;
    }


    @Getter
    public class TokenInfo {
        private String username;
        private Date issuedAt;
        private Date expire;

        public TokenInfo(String username, Date issuedAt, Date expire) {
            this.username = username;
            this.issuedAt = issuedAt;
            this.expire = expire;
        }
    }
}
