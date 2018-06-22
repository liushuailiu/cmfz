package com.fly.service.mysql.permission;

import com.fly.dao.mysql.system.LogMessageMapper;
import com.fly.pojo.LogMessage;
import com.fly.util.aop.LogAnn;
import com.fly.util.aop.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogMessageService {

    @Autowired
    private LogMessageMapper mapper;

    @LogAnn(describe = Log.SELECT_STUDENT)
    public LogMessage selectLogMessageById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }



}
