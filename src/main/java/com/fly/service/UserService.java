package com.fly.service;

import com.fly.dao.SystemUserMapper;
import com.fly.pojo.SystemUser;
import com.fly.util.aop.SystemLogAnnotation;
import com.fly.util.aop.SystemLogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @SystemLogAnnotation(describe = SystemLogProperties.USER_LOGIN)
    public SystemUser loginUser(String name, String pass) {
        return systemUserMapper.selectByNameAndPass(name,pass);
    }

    @SystemLogAnnotation(describe = SystemLogProperties.USER_PERMISSION_ALL)
    public List<String> getUserPermissionByUserId(Integer userid) {

        return systemUserMapper.getPermissionByUserId(userid);
    }
}
