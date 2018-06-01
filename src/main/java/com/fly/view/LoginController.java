package com.fly.view;

import com.fly.auth.JsonWebToken;
import com.fly.auth.Token;
import com.fly.pojo.SystemUser;
import com.fly.service.UserService;
import com.fly.util.system.SystemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/login",name = "登录")

public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/confirm")
    public Object confirm(String name,String pass){

        SystemUser systemUser = userService.loginUser(name,pass);
        if(systemUser==null)
            return new SystemResult("用户名或密码错误",0);
        if(systemUser.getUserislockout())
            return new SystemResult("账号已被禁用,解锁请联系 : QQ 1779905848",0);
        //根据用户ID得到用户的所有权限
        List<String> userPermissions = userService.getUserPermissionByUserId(systemUser.getUserid());
        //用户登录令牌对象
        Token token = new Token(systemUser.getUserid(),userPermissions);
        try {
            String tokenString = JsonWebToken.sign(token,4*60*60*1000);
            System.out.println(tokenString + "-------" + tokenString.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HashMap map = new HashMap();
        map.put("TOKEN",token);
        map.put("PERMISSION",userPermissions);

        return new SystemResult(map);
    }


}
