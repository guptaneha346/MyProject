package com.myproject.controller;

import com.myproject.Service.UserService;
import com.myproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView();
        List<User> userList = userService.getAllDetails();
        model.addObject("userList", userList);

        model.setViewName("user/Show_list");

        return model;
    }


    @RequestMapping(value="/addUser/", method=RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView model = new ModelAndView();

        User user = new User();
        model.addObject("userForm", user);
        model.setViewName("user/user_form");

        return model;
    }

    @RequestMapping(value="/uploadFile/", method=RequestMethod.GET)
    public ModelAndView addUser1() {
        ModelAndView model = new ModelAndView();

        User user = new User();
        model.addObject("file", user);
        model.setViewName("user/upload_form");

        return model;
    }



    @RequestMapping(value="/updateUser/{id}", method=RequestMethod.GET)
    public ModelAndView editUser(@PathVariable long id) {
        ModelAndView model = new ModelAndView();

        User user = userService.getUserById(id);
        model.addObject("userForm", user);
        model.setViewName("user/user_form");

        return model;
    }

    @RequestMapping(value="/saveUser", method=RequestMethod.POST)
    public ModelAndView save1(@ModelAttribute("userForm") User user) {
        userService.saveOrUpdate(user);

        return new ModelAndView("redirect:/user/list");
    }



    @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        userService.deleteUser(id);

        return new ModelAndView("redirect:/user/list");
    }





}