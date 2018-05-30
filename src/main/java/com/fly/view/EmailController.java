package com.fly.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@CrossOrigin
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/go")
    public void sendMail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1779905848@qq.com");
        mailMessage.setTo("1779905848@qq.com");
        mailMessage.setSubject("放假通知");
        mailMessage.setText("明天放假");
        mailSender.send(mailMessage);
    }

}
