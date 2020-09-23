package com.latico.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-09-23 14:45
 * @version: 1.0
 */
@RestController
@RequestMapping("demo/mail")
public class DemoMailController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailProperties mailProperties;

    @GetMapping(value = "sendSimpleMail")
    public boolean sendSimpleMail(String targetMail, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername()); // 邮件发送者
        message.setTo(targetMail); // 邮件接受者
        message.setSubject(subject); // 主题
        message.setText(content); // 内容

        mailSender.send(message);
        return true;
    }
}
