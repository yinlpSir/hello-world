package com.hngy.lms.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hngy.lms.entity.User;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 *  secret and validTime 去 application.properties 里去取
 *  Generate token and Verify token
 */
public class JWTUtil {
    private static final String secret="qwersadfjsdfsdfsdfkl";//jwt 加密密钥
    private static long validTime = 60 * 40;//jwt有效时间，从生成这个jwt时算起，这里是40分钟
    private static ObjectMapper objectMapper = new ObjectMapper();

    //生成json web token
    public static String generateToken(User user)throws JsonProcessingException {
        Instant now = Instant.now();
            //设置头、负载、签名 （头可以不设置，有默认值）
        Map<String,Object> map=new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        map.put("role",user.getAuthorities());
        //设置头、负载、签名 （头可以不设置，有默认值）
        return JWT.create()//创建JWTBuilder
                .withClaim("userInfo", objectMapper.writeValueAsString(map))//自定义属性.payload
                .withExpiresAt(now.plusSeconds(validTime))//超时时间
                .sign(Algorithm.HMAC512(secret));//设置签名
    }

    /**
     * verify token
     *
     * AlgorithmMismatchException – if the algorithm stated in the token's header is not equal to the one defined in the JWTVerifier.
     * SignatureVerificationException – if the signature is invalid.
     * TokenExpiredException – if the token has expired.
     * MissingClaimException – if a claim to be verified is missing.
     * IncorrectClaimException – if a claim contained a different value than the expected one
     */
    public static HashMap<String,Object> parseToken(String token) throws JsonProcessingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secret)).build();//获取jwt校验器
        DecodedJWT jwt = verifier.verify(token);//验证token,它会抛出 JWTVerificationException 或其 子类异常 以表示 token 出现错误
        String userInfo = jwt.getClaim("userInfo").asString();//获取token负载的userInfo属性值
        //jwt.getExpiresAt()); // 获取过期时间
        return objectMapper.readValue(userInfo, HashMap.class);
    }
}
