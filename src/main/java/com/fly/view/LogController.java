package com.fly.view;

import com.fly.pojo.LogMessage;
import com.fly.service.LogMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/log",name = "日志管理")
public class LogController {

    @Autowired
    private LogMessageService logMessageService;

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT,name = "查看指定ID日志权限")
    public LogMessage selectLog(@PathVariable("id") Integer id){
        return logMessageService.selectLogMessageById(id);
    }


}
