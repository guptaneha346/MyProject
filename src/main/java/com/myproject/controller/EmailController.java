package com.myproject.controller;

import com.myproject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmailController {


    @RequestMapping(value= {"/emailpage"}, method=RequestMethod.GET)
    public ModelAndView emailpage() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/EmailRequest");

        return model;
    }


}


