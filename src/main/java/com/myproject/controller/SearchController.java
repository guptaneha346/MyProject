package com.myproject.controller;

import com.myproject.Service.UserService;
import com.myproject.model.User;
import com.myproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/search1")
public class SearchController {

    @Autowired
    UserService userService;


    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/search")
    public String search(Model model, User user, @RequestParam("name") String name ,Model model1, @RequestParam("department") String department) {
        if (name.equals(name) && department.equals("")) {

            System.out.println("1111111");

            model.addAttribute("userList", userService.findByName(name));


            return "user/bootstarpexample";

        } else if (name.equals("") && department.equals(department)) {

            System.out.println("22222222222");
            model.addAttribute("userList", userService.findByDepartment(department));

            return "user/bootstarpexample";
        } else if (name.equals(name) && department.equals(department)) {
            System.out.println("33333333");

            model.addAttribute("userList", userService.findByNameAndDepartment(name, department));

            return "user/bootstarpexample";
        } else
            return "user/Show_list1";


    }

    }


