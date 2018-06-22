package com.fly.service;

import com.fly.dao.SystemRoleMapper;
import com.fly.dao.SystemUserMapper;
import com.fly.pojo.SystemRole;
import com.fly.pojo.SystemUser;
import com.fly.util.Page;
import com.fly.util.aop.LogAnn;
import com.fly.util.aop.Log;
import com.fly.util.system.IpUtils;
import com.fly.util.system.PasswordEncoder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author fly
 */
@Service
public class UserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @LogAnn(describe = Log.USER_LOGIN)
    public SystemUser loginUser(String name, String pass) {

        PasswordEncoder passwordEncoder = new PasswordEncoder("tom", "MD5");
        pass = passwordEncoder.encode(pass, 5);
        return systemUserMapper.selectByNameAndPass(name, pass);
    }

    @LogAnn(describe = Log.USER_PERMISSION_ALL)
    public List<String> getUserPermissionByUserId(Integer userid) {

        return systemUserMapper.getPermissionByUserId(userid);
    }

    @LogAnn(describe = Log.USER_ROLES_ALL)
    public List<SystemRole> getUserRolesByUserId(Integer userid) {
        return systemUserMapper.getUserRolesByUserId(userid);
    }

    @LogAnn(describe = Log.USER_ROLES_ID_ALL)
    public List<Integer> getUserRolesIDByUserId(Integer userid) {
        return systemUserMapper.getUserRolesIDByUserId(userid);
    }

    public List<Integer> getModuleByRoles(List<Integer> roles) {
        return null;
    }

    public Page queryUser(Integer page, Integer limit, String username) {

        PageHelper.startPage(page, limit);
        List<SystemUser> systemUsers = systemUserMapper.selectUser(username);
        PageInfo pageInfo = new PageInfo(systemUsers);
        return new Page(pageInfo);

    }

    public Page queryUserRoles(Integer page, Integer limit, Integer userId, String name) {
        PageHelper.startPage(page, limit);
        List<SystemRole> systemRoles = systemRoleMapper.selectUserRoles(userId, name);

        if (systemRoles != null) {
            for (SystemRole s : systemRoles) {
                if (s.getUserId() == null) {
                    s.setUserId(Integer.MIN_VALUE);
                }
            }
        }

        PageInfo pageInfo = new PageInfo(systemRoles);
        return new Page(pageInfo);
    }

    public Page userLostRole(Integer user, Integer role) {

        Integer count = systemRoleMapper.deleteUserRole(user, role);

        Page page = count > 0 ? new Page(200) : new Page(500);

        return page;

    }

    public Page userGetRole(Integer user, Integer role) {

        Integer count = systemRoleMapper.userGetRole(user, role);

        Page page = count > 0 ? new Page(200) : new Page(500);

        return page;

    }

    public Page insertUser(SystemUser systemUser) {

        String pass = systemUser.getUserpassword();
        PasswordEncoder passwordEncoder = new PasswordEncoder("tom", "MD5");
        pass = passwordEncoder.encode(pass, 5);
        systemUser.setUserpassword(pass);
        System.out.println(pass);
        int count = systemUserMapper.insertSelective(systemUser);
        Page page = count > 0 ? new Page(200) : new Page(500);
        return page;

    }

    public void updateUserWrongCount(String name) {

        systemUserMapper.updateUserWrongCount(name);

    }

    public Page updateUserWrongCount(Integer user) {
        Integer count = systemUserMapper.updateUserWrongCountById(user);
        Page page = count > 0 ? new Page(200) : new Page(500);
        return page;
    }

    public Page updateUserType(Integer user) {
        systemUserMapper.updateUserTypeWrongCountById(user);
        Integer count = systemUserMapper.updateUserType(user);
        Page page = count > 0 ? new Page(200) : new Page(500);
        return page;
    }

    public void updateLoginType(HttpServletRequest request, SystemUser systemUser) {

        systemUser.setUserlastloginip(IpUtils.getRemoteHost(request));
        systemUser.setUserlastlogintime(new Date());
        systemUserMapper.updateByPrimaryKeySelective(systemUser);

    }

    public Page queryUserRoleByConsultant(Integer page, Integer limit) {

        PageHelper.startPage(page,limit);
        List<SystemUser> list = systemUserMapper.queryUserRoleByConsultant("咨询师");
        PageInfo pageInfo = new PageInfo(list);
        return new Page(pageInfo);

    }
}
