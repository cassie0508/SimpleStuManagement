package com.springboot.stumanage.controller;

import com.springboot.stumanage.entity.SC;
import com.springboot.stumanage.entity.SCRepository;
import com.springboot.stumanage.entity.User;
import com.springboot.stumanage.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StuController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SCRepository scRepository;

    @RequestMapping(value = "/student/choose", method = RequestMethod.POST)
    public String func1(@ModelAttribute User user)
    {
        //System.out.println("in stu "+sc);
        return "stuChoose";
    }

    @RequestMapping(value = "/student/find", method = RequestMethod.POST)
    public String func2(@ModelAttribute User user)
    {
        return "stuFind";
    }
}
