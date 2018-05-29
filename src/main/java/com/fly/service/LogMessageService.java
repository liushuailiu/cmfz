package com.fly.service;

import com.fly.dao.LogMessageMapper;
import com.fly.pojo.LogMessage;
import com.fly.util.aop.SystemLogAnnotation;
import com.fly.util.aop.SystemLogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogMessageService {

    @Autowired
    private LogMessageMapper mapper;

    @SystemLogAnnotation(describe = SystemLogProperties.SELECT_STUDENT)
    public LogMessage selectLogMessageById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }



}
