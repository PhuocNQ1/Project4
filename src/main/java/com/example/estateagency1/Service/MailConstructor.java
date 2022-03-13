package com.example.estateagency1.Service;

import com.example.estateagency1.DTO.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service
public class MailConstructor {
    @Autowired
    private TemplateEngine templateEngine;
    // Cấu hình mail gửi đến khách hàng

    public SimpleMailMessage simpleMailMessage(Mail mail){
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getUser_receiver());
        message.setSubject("Test Simple Email "+mail.getTitle());
        message.setText("Hello, Im testing Simple Email" +mail.getContent());

        return message;
    }

}

