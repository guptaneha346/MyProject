package com.myproject.controller;

import javax.validation.Valid;

import com.myproject.Service.UserService;

import com.myproject.model.EmployeeSignup;
import com.myproject.model.User;
import com.myproject.model.UserSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class EmployeeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = { "/login1" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        UserSignup user = new UserSignup();
        model.addObject("user", user);
        model.setViewName("user/signup");

        return model;
    }

    @RequestMapping(value = "/employeeregister", method = RequestMethod.GET)
    public ModelAndView signup1() {
        ModelAndView model = new ModelAndView();
        EmployeeSignup user = new EmployeeSignup();
        model.addObject("user", user);
        model.setViewName("user/EmployeeSignup");

        return model;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView list1() {
        ModelAndView model = new ModelAndView();
        List<User> userList = userService.getAllDetails();
        model.addObject("userList", userList);

        model.setViewName("user/EmployeeList");

        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView();

        List<User> userList = userService.getAllDetails();


        model.addObject("userList", userList);

        model.setViewName("user/bootstarpexample");

        return model;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView createUser(@Valid UserSignup user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        UserSignup userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new UserSignup());

            model.setViewName("user/login");
        }

        return model;
    }



    @RequestMapping(value="/employeeregister", method=RequestMethod.POST)
    public ModelAndView createEmployee(@Valid EmployeeSignup user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        UserSignup userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveEmployee(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new EmployeeSignup());

            model.setViewName("user/login");
        }

        return model;
    }
}
