package com.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.myproject.model.User;
import com.myproject.model.UserSignup;

import com.myproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/login");

        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        UserSignup user = new UserSignup();
        model.addObject("user", user);
        model.setViewName("user/signup");

        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
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


    @RequestMapping(value="/list1", method= RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView();
        List<User> userList = userService.getAllDetails();
        model.addObject("userList", userList);

        model.setViewName("user/user_list");

        return model;
    }


         @GetMapping(value = "/search")
         public  String search(Model model, @RequestParam("name") String name,Model model1,@RequestParam("department") String department) {
             if(name.equals(name)&&department.equals("")){
                 System.out.println("1111111");
                 model.addAttribute("userList", userService.findByName(name));
                 if(!(name.equals(userService.findByName(name))))
                 {
                     System.out.println("1.1111111.11");
                     return "user/Show_list1";
                 }
                 return "user/user_list";}

             else if(name.equals("")&&department.equals(department)){

                 System.out.println("22222222222");
                 model.addAttribute("userList", userService.findByDepartment(department));
                 if(!(name.equals(userService.findByDepartment(department))))
                 {
                     System.out.println("1.1111111.11");
                     return "user/Show_list1";
                 }
                 return "user/user_list";}

             else if(name.equals(name)&&department.equals(department)){
                 System.out.println("33333333");
                 model.addAttribute("userList",userService.findByName(name));
                 model.addAttribute("userList",userService.findByDepartment(department));
                 if(!(name.equals(userService.findByDepartment(department)))&&!(department.equals(userService.findByDepartment(department))))
                 {
                     System.out.println("1.1111111.11");
                     return "user/Show_list1";
                 }
                 return "user/user_list";
             }


             else
                 return "user/user_list";


         }

         @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserSignup user = userService.findUserByEmail(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.setViewName("/user/list");
        return model;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}