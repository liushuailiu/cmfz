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

    //用户登录操作
    @PostMapping("/confirm")
    public Object confirm(String name,String pass){

        SystemUser systemUser = userService.loginUser(name,pass);
        if(systemUser==null)
            return new SystemResult("用户名或密码错误",1);
        if(systemUser.getUserislockout())
            return new SystemResult("账号已被禁用,解锁请联系 : QQ 1779905848",1);
        //根据用户ID查找到用户的所有角色ID
        List<Integer> roles = userService.getUserRolesIDByUserId(systemUser.getUserid());
        //根据用户ID得到用户的所有权限
        List<String> userPermissions = userService.getUserPermissionByUserId(systemUser.getUserid());
        //用户登录令牌对象
        Token token = new Token(systemUser.getUserid(),userPermissions);
        String tokenString = "";
        try {
            //将TOKEN对象加密
            tokenString = JsonWebToken.sign(token,4*60*60*1000);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HashMap map = new HashMap();
        //存入口令
        map.put("TOKEN",tokenString);
        //存入权限集合
        map.put("PERMISSION",userPermissions);
        //存入用户ID
        map.put("USER_ID",systemUser.getUserid());
        //用户具有的角色
        map.put("ROLES",roles);

        map.put("USERNAME",systemUser.getUsername());

        return new SystemResult(0,map);
    }


}
