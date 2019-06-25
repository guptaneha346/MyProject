package com.myproject.controller;

import com.myproject.Service.UserService;
import com.myproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView();

        List<User> userList = userService.getAllDetails();

        model.addObject("userList", userList);

        model.setViewName("user/bootstarpexample");

        return model;
    }

    @RequestMapping(value = "/addUser/", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView model = new ModelAndView();

        User user = new User();
        model.addObject("userForm", user);
        model.setViewName("user/user_form");

        return model;
    }


    @RequestMapping(value = "/uploadUserFile/", method = RequestMethod.GET)
    public ModelAndView addUser1() {
        ModelAndView model = new ModelAndView();

        User user = new User();
        model.addObject("file", user);
        model.setViewName("user/upload_file");

        return model;
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable long id) {
        ModelAndView model = new ModelAndView();

        User user = userService.getUserById(id);
        model.addObject("userForm", user);
        model.setViewName("user/update_form");

        return model;
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView save1(@ModelAttribute("userForm") User user) {
        userService.saveOrUpdate(user);


        return new ModelAndView("redirect:/user/list");
    }


    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        userService.deleteUser(id);

        return new ModelAndView("redirect:/user/list");
    }

    @GetMapping(value = "/file")
    public String home(Model model) {

        model.addAttribute("user", new User());


        List<User> users = userService.getAllDetails();

        model.addAttribute("users", users);

        return "redirect:/user/list";

    }


    @PostMapping(value = "/UploadFile/")
    public String uploadfile(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        boolean isFlag = userService.savDatafromuploadfile(user.getFile());
        boolean row=userService.CsvRowError();

        if (isFlag) {
            System.out.println("uploadfile");

        }
        else {
            System.out.println("try again");
            return "user/CsvError";
        }
        return "redirect:/user/file";
    }

    @GetMapping(value = "/error")
    public String csverror(){
        return "user/CsvError";
    }
}