package com.myproject.controller;

import com.myproject.Service.MailService;
import com.myproject.Service.UserService;

import com.myproject.model.EmployeeSignup;
import com.myproject.model.User;

import com.myproject.model.UserSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.util.List;


@RestController
public class EmailController {

    @Autowired
    private MailService notificationService;


@Autowired
EmployeeSignup employeeSignup;

@Autowired
User user;

    @Autowired
    UserService userService;

    @RequestMapping(value= "/Email-page/{id}", method= RequestMethod.GET)
    public ModelAndView email(@PathVariable long id) {
        ModelAndView model = new ModelAndView();
        User user = userService.getUserById(id);
        model.addObject("userEmail", user);
        model.setViewName("user/EmailRequest");

        return model;
    }

    @RequestMapping("/send-mail")
    public ModelAndView send1(@ModelAttribute("userEmail") User email, ModelMap modelMap) {

        email.setEmail(email.getEmail());
      employeeSignup.setEmail(employeeSignup.getEmail());
        email.setMessage(email.getMessage());

        System.out.println(email);
        System.out.println(employeeSignup);

        try {
            notificationService.sendEmail(email);
        } catch (MailException mailException) {
            System.out.println(mailException);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return new ModelAndView("redirect:/sendsuccess");
    }


    @RequestMapping(value= {"/sendsuccess"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();

        List<User> userList = userService.getAllDetails();

        model.addObject("userList", userList);

        model.setViewName("user/EmployeeList");

        return model;
    }



}
