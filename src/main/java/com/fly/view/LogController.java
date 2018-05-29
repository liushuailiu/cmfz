package com.fly.view;

import com.fly.pojo.LogMessage;
import com.fly.service.LogMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogMessageService logMessageService;

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public LogMessage selectLog(@PathVariable("id") Integer id){
        return logMessageService.selectLogMessageById(id);
    }

}
