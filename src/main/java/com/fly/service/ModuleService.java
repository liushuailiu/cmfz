package com.fly.service;

import com.fly.dao.SystemModuleMapper;
import com.fly.pojo.SystemModule;
import com.fly.util.Page;
import com.fly.util.aop.SystemLogAnnotation;
import com.fly.util.aop.SystemLogProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private SystemModuleMapper moduleMapper;

    @SystemLogAnnotation(describe = SystemLogProperties.MODULE_ROLES_USER)
    public List<SystemModule> queryModuleTree(Integer[] roles) {
        return moduleMapper.queryModuleTreeByRoleID(roles);
    }

    /**
     * 查询所有模块,超级管理员拥有此权限
     * @param page 第几页
     * @param limit 每页显示几条数据
     * @return systemModule 集合
     */
    public Object queryModuleAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<SystemModule> modules = moduleMapper.selectSystemModule();
        PageInfo pageInfo = new PageInfo(modules);
        Page result = new Page(pageInfo);
        return result;
    }


    public Page updateModuleById(SystemModule module) {
        Integer count = moduleMapper.updateByPrimaryKeySelective(module);
        Page page = count<=0 ? new Page(500,"修改失败"):new Page(200,"修改成功");
        return page;
    }

    /**
     * 维护角色与模块的关系,删除指定角色下的某个模块
     * @param module 模块ID
     * @param role   角色ID
     * @return
     */
    public Page deleteModuleByModuleId(Integer module, Integer role) {
        Integer count = moduleMapper.deleteRoleModuleByModuleAndRole(module,role);
        Page page = count<=0 ? new Page(500,"删除失败"):new Page(200,"删除成功");
        return page;
    }


    public Page insertModule(SystemModule systemModule) {

         Page page = moduleMapper.insertSelective(systemModule)<1 ? new Page(500,"添加失败") : new Page(200,"添加成功");
         return page;

    }

    /**
     * 查询角色下的所有模块
     * @param role  角色ID
     * @param page  第几页
     * @param limit 每页显示几条
     * @return 页面集合
     */
    public Page queryModuleByRoleId(Integer role, Integer page, Integer limit) {

        PageHelper.startPage(page,limit);
        List<SystemModule> modules = moduleMapper.selectSystemModuleByRoleId(role);
        PageInfo pageInfo = new PageInfo(modules);
        Page pages = new Page(pageInfo);
        return pages;

    }

    /**
     * 查询所有模块,用Tree来显示
     * @return
     */
    public List<SystemModule> queryModuleAll() {
        return moduleMapper.selectSystemModule();
    }

    public List<SystemModule> queryModuleByRoleId(Integer role) {
        return moduleMapper.selectSystemModuleByRoleId(role);
    }

    public List<SystemModule> queryModuleByNotRoleId(Integer role) {
        return moduleMapper.selectSystemModuleByNotRoleId(role);
    }

    public Page updateRoleModuleAppend(Integer[] module, Integer role) {
        Page page =  moduleMapper.insertRoleModule(module,role)>0 ? new Page(200) : new Page(500);
        return page;
    }
}
