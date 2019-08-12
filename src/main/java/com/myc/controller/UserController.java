package com.myc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/userLogin")
    public String userLogin() {
        return "user/userLogin";
    }

    @RequestMapping("/register")
    public String register() {
        return "user/userRegister";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword() {
        return "user/updatePassword";
    }

    @RequestMapping("/retrievePassword")
    public String retrievePassword() {
        return "user/retrievePassword";
    }

    @RequestMapping("/exit")
    public String exit() {
        return "redirect:/bookList/1";
    }
}
