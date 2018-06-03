package com.fly.service;

import com.fly.dao.SystemModuleMapper;
import com.fly.pojo.SystemModule;
import com.fly.util.aop.SystemLogAnnotation;
import com.fly.util.aop.SystemLogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private SystemModuleMapper moudleMapper;

    @SystemLogAnnotation(describe = SystemLogProperties.MODULE_ROLES_USER)
    public List<SystemModule> queryModuleTree(Integer[] roles) {

        return moudleMapper.queryModuleTreeByRoleID(roles);

    }

    /**
     * 查询系统模块
     * @return
     */

}
