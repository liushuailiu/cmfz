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

    public Page deleteModuleByModuleId(Integer id) {
        Integer count = moduleMapper.deleteByPrimaryKey(id);
        Page page = count<=0 ? new Page(500,"删除失败"):new Page(200,"删除成功");
        return page;
    }
}
