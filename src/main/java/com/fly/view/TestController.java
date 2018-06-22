package com.fly.view;

import com.fly.pojo.academic.Students;
import com.fly.pojo.oracle.scott.Emp;
import com.fly.service.oracle.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author fly
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EmpService empService;

    @GetMapping("/oracle")
    public List<Emp> selectAll(){
        return empService.selectAllEmp();
    }

}
