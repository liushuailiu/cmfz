package com.fly.view;

import com.fly.pojo.oracle.scott.Emp;
import com.fly.service.oracle.EmpService;
import com.fly.service.rabbit.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author fly
 */
@RestController
@RequestMapping("/test")
@Configuration
@EnableAsync
public class TestController {

    public static CopyOnWriteArraySet<TestController> controller =  new CopyOnWriteArraySet<TestController>();
    public ThreadLocal<Boolean> threadLocal = new ThreadLocal<Boolean>();

    public void setResult(Boolean b){
        this.threadLocal.set(b);
    }

    @Autowired
    private EmpService empService;

    @Autowired
    private Producer producer;

    @GetMapping("/oracle")
    public List<Emp> selectAll(){
        return empService.selectAllEmp();
    }

    @GetMapping("/login/test")
    public void login() throws InterruptedException {
        producer.testEmail();
        producer.testVerification();
        producer.testPhone();
    }

    

}
