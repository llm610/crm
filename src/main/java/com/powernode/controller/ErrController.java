package com.powernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/err")
public class ErrController {

    @RequestMapping("indexView")
    public String err(Model model){

       /* model.addAttribute("msg","我的世界");*/
        model.addAttribute("msg","我的世界");
        System.out.println("hha");
        return "/err";
    }
}
