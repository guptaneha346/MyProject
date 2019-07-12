package com.myproject.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.myproject.model.EmployeeSignup;
import com.myproject.model.User;
import com.myproject.model.UserSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;




@Service
public class MailService {

    private JavaMailSender javaMailSender;


    @Autowired
    public MailService(JavaMailSender javaMailSender) {


        this.javaMailSender = javaMailSender;
    }

@Autowired
EmployeeSignup employeeSignup;

    public void sendEmail(User user) throws MailException, MessagingException {


        SimpleMailMessage mail = new SimpleMailMessage();
MimeMessage mimeMessage=javaMailSender.createMimeMessage();
MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessage.setFrom("ng87311@gmail.com");
        mimeMessageHelper.setText(user.getMessage());



        mimeMessageHelper.setSubject("Request for phone number");

        javaMailSender.send(mimeMessage);
    }



}