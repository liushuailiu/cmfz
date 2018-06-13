package com.fly.view;

import com.fly.auth.JsonWebToken;
import com.fly.auth.Token;
import com.fly.pojo.SystemUser;
import com.fly.service.UserService;
import com.fly.util.Page;
import com.fly.util.system.IpUtils;
import com.fly.util.system.SystemResult;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/login")

public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 注册新用户
     * @return
     */
    /**
     * 注册新用户
     * @param systemUser
     * @return
     */
    @PostMapping("/register")
    public Page registerUser(SystemUser systemUser, HttpServletRequest request){

        String IP = IpUtils.getRemoteHost(request);
        //设置系统创建时间
        systemUser.setUsercreatetime(new Date());
        systemUser.setUserlastloginip(IP);
        //设置上次登录时间
        systemUser.setUserlastlogintime(new Date());
        return userService.insertUser(systemUser);
    }

    /**
     * 封号
     * @param user
     * @return
     */
    @PostMapping("/seal")
    public Page sealUser(@RequestParam("userId") Integer user){
        return userService.updateUserWrongCount(user);
    }

    /**
     * 账号回复正常
     * @param user
     * @return
     */

    @PostMapping("/normal")
    public Page normalUser(@RequestParam("userId") Integer user){
        return userService.updateUserType(user);
    }

    /**
     * 给用户分配角色
     * @param user 用户ID
     * @param role 角色ID
     * @return
     */

    @PostMapping("/get")
    public Page userGetRole(@RequestParam("user")Integer user,@RequestParam("role")Integer role){

        return userService.userGetRole(user,role);

    }

    /**
     * 回收用户角色
     * @param user 用户ID
     * @param role 角色ID
     * @return
     */

    @PostMapping("/lost")
    public Page userLostOrGetRole(@RequestParam("user")Integer user , @RequestParam("rId") Integer role){

        return userService.userLostRole(user,role);

    }


    /**
     * 查询用户拥有以及未拥有的角色
     * @return
     */
    @PostMapping("/queryRole")
    public Page queryUserRoles(Integer page , Integer limit , @RequestParam("user") Integer userId
            , @RequestParam(value = "username",required = false) String name){
        return userService.queryUserRoles(page , limit , userId , name );
    }

    /**
     * 查询目前存在于系统中的所有用户
     * @return
     */
    @PostMapping("/query")
    public Page queryUser(Integer page, Integer limit, @RequestParam(value = "username" ,required = false) String username){
        return userService.queryUser(page,limit,username);
    }


    //用户登录操作
    @PostMapping(value = "/confirm")
    public Object confirm(String name,String pass,HttpServletRequest request){

        SystemUser systemUser = userService.loginUser(name,pass);
        if(systemUser==null){
            userService.updateUserWrongCount(name);
            return new SystemResult("用户名或密码错误",1);
        }
        if(systemUser.getUserislockout())
            return new SystemResult("账号已被禁用,解锁请联系 : QQ 1779905848",1);

        userService.updateLoginType(request,systemUser);

        //根据用户ID查找到用户的所有角色ID
        List<Integer> roles = userService.getUserRolesIDByUserId(systemUser.getUserid());
        //根据用户ID得到用户的所有权限
        List<String> userPermissions = userService.getUserPermissionByUserId(systemUser.getUserid());
        //用户登录令牌对象
        Token token = new Token(systemUser.getUserid(),systemUser.getUsername(),userPermissions);
        String tokenString = "";
        try {

            //将TOKEN对象加密
            tokenString = JsonWebToken.sign(token,4*60*60*1000);
            //将TOKEN存入到redis
            redisTemplate.opsForValue().set(systemUser.getUsername(),tokenString,4,TimeUnit.HOURS);

        } catch (IOException e) {
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
