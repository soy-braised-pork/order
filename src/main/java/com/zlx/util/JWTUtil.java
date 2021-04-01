package com.zlx.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlx.entry.User;
import lombok.SneakyThrows;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static final String SECRET = "!=end=";
    private static final String PAYLOAD = "payload";
    private static final String EXP = "exp";

    //加密，传入一个对象和有效期
    @SneakyThrows
    public static <T> String sign(T object, long maxAge) {
        // new JWTSigner().var  回车
        JWTSigner jwtSigner = new JWTSigner(SECRET);
        final Map<String, Object> claims = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, json);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return jwtSigner.sign(claims);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    //解密，传入一个加密后 token 字符串和解密后的类型
    public static <T> T unSign(String jwt,Class<T> tClass){
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String,Object> claims=verifier.verify(jwt);
            if (claims.containsKey(EXP)&&claims.containsKey(PAYLOAD)){
                long exp=(long) claims.get(EXP);
                long currentTimeMillis=System.currentTimeMillis();
                if (exp>currentTimeMillis){
                    String json= (String) claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json,tClass);
                }
            }
            return null;
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException | SignatureException | JWTVerifyException e) {
            e.printStackTrace();
            return null;
        }
    }
}
