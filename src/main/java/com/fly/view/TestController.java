package com.fly.view;

import com.fly.pojo.teaching.Students;
import com.fly.service.JspTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author fly
 */
@Controller
@RequestMapping("/test/jsp")
public class TestController {

    @Autowired
    JspTest jspTest;

    @GetMapping("/sel")
    public ModelAndView testJsp(){
        List<Students> students = jspTest.select();
        ModelAndView model= new ModelAndView("index");
        model.addObject("student",students);
        return model;
    }

    @GetMapping("/test1")
    public String test(HttpServletRequest request){
        request.setAttribute("name","AAA");
        return "index";
    }
}
