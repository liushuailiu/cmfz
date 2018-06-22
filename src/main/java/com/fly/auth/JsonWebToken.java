package com.fly.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * toekn生成与解析
 * @author fly
 */
public class JsonWebToken {

    private static final String SECRET = "secret";
    private static Gson gson = new Gson();

    /**
     * 创建JSON_WEB_TOKEN头部
     * @return
     */
    private static Map<String,Object> createHead(){
        Map<String , Object> map = new HashMap(16);
        map.put("typ","JWT");
        map.put("alg","HS256");
        return map;
    }

    /**
     * 获取token
     * @param obj  对象数据
     * @param maxTime 有效期
     * @param <T>
     * @return
     * @throws UnsupportedEncodingException
     */
    public static <T> String sign(T obj,long maxTime) throws UnsupportedEncodingException {
        JWTCreator.Builder builder = JWT.create();
        builder.withHeader(createHead())
               .withSubject(gson.toJson(obj));
        if(maxTime>0){
            Date exp = new Date(System.currentTimeMillis()+maxTime);
            builder.withExpiresAt(exp);
        }
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解密
     * @param token
     * @param tClass
     * @param <T>
     * @return
     * @throws UnsupportedEncodingException
     */
    public static <T> T unSign(String token, Class<T> tClass) throws IOException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Date date = decodedJWT.getExpiresAt();
        if(date!=null && date.after(new Date())){
            String subject = decodedJWT.getSubject();
            return gson.fromJson(subject,tClass);
        }
        return null;
    }


}
