package com.techjava.springbootemaildemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender  mailSender;

    @GetMapping("/sent")
    public String sentMail() throws MessagingException {
        formMail(false);
        return "Email Sent";
    }

    @GetMapping("/attach")
    public String sentMailAttachment() throws MessagingException {
        formMail(true);
        return "Email Sent with Attachment";
    }

    @GetMapping("/attachinline")
    public String sentMailAttachmentInLine() throws MessagingException {
        formMailInline(true);
        return "Email Sent with Inline Attachment";
    }

    private void formMailInline(boolean isAttachment) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo("xxxx@gmail.com");
        messageHelper.setText("<html><body>Here is a Logo! <img src='cid:logo.png'/><body></html>", true);
        messageHelper.setSubject("Test Mail");
        if(isAttachment){
            ClassPathResource  classPathResource = new ClassPathResource("logo.png");
            messageHelper.addAttachment("logo.png",classPathResource);
        }
        mailSender.send(mimeMessage);
    }


    private void formMail(boolean isAttachment) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo("sharmaragi84@gmail.com");
        messageHelper.setText("Test Mail from Spring boot");
        messageHelper.setSubject("Test Mail");
        if(isAttachment){
            ClassPathResource  classPathResource = new ClassPathResource("logo.png");
            messageHelper.addAttachment("logo.png",classPathResource);
        }
        mailSender.send(mimeMessage);
    }
}
