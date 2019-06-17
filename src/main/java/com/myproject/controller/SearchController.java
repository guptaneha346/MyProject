package com.myproject.controller;


import com.myproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/search1")
public class SearchController {

    @Autowired
    UserService userService;
@RequestMapping(value = "/search/{name}/{department}",method = RequestMethod.GET)
public String printWelcome(@PathVariable("name") String name,
                           @PathVariable("department") String department, ModelMap model,
                           HttpServletRequest request){

        model.addAttribute("userList", userService.findByName(name));
    model.addAttribute("userList", userService.findByDepartment(department));
        return "user/Show_list";
    }



}
