package com.fly.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mail",name = "邮件管理")
@CrossOrigin
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping(value = "/send",name = "发送邮件权限")
    public void sendMail(){
//        建立邮箱发送类
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        发件人
        mailMessage.setFrom("1779905848@qq.com");
//        收件人
        mailMessage.setTo("1779905848@qq.com");
//        邮箱主题
        mailMessage.setSubject("放假通知");
//        邮箱内容
        mailMessage.setText("明天放假");
//        发送
        mailSender.send(mailMessage);
    }

}
