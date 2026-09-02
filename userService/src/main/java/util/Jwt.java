package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jwt {

    private static final String jwtSecret = "123456ab#^&*123456ab#^&*123456ab#^&*";

    public static String creatToken(Integer userId) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());// 生成密钥

        return Jwts.builder()
                .claims(claims)// 自定义加密内容
                .issuedAt(new Date())// 签发时间
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))// 过期时间
                .signWith(key)// 签名
                .compact();
    }

    public static Map<String, Object> checkToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            Claims claims = Jwts.parser()
                    .verifyWith(key)// 验证签名
                    .build()
                    .parseSignedClaims(token)// 解析 token
                    .getPayload();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

