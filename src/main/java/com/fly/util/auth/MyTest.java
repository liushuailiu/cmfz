package com.fly.util.auth;


import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

public class MyTest {

    @Test
    public void testWebToken() throws UnsupportedEncodingException {
        String user = "我是张三";
        String token = JsonWebToken.sign(user,10000);
        System.out.println("token>>>>>>>>>>>"+token);
        String resultUser = JsonWebToken.unsign(token,String.class);
        System.out.println(resultUser);
    }

}
