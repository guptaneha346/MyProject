package com.myproject.controller;


import com.myproject.Service.UserService;
import com.myproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/search1")
public class SearchController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/search")
    public  String search(Model model, @RequestParam("name") String name,Model model1,@RequestParam("department") String department) {

        if(name.equals(name)&&department.equals("")){


            model.addAttribute("userList", userService.findByName(name));

return "user/Show_list";
        }

        else if(name.equals("")&&department.equals(department)){


            model.addAttribute("userList", userService.findByDepartment(department));


            return "user/user_list";
        }

        else if(name.equals(name)&&department.equals(department)){

            model.addAttribute("userList",userService.findByName(name));
            model.addAttribute("userList",userService.findByDepartment(department));

            return "user/user_list";
        }


        else
            return "user/user_list";


    }

}


