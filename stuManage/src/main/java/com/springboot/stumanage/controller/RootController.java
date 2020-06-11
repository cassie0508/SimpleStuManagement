package com.springboot.stumanage.controller;

import com.springboot.stumanage.entity.User;
import com.springboot.stumanage.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/root/func/add", method = RequestMethod.POST)
    public String func1()
    {
        return "rootAdd";
    }

    @RequestMapping(value = "/root/func/del", method = RequestMethod.POST)
    public String func2()
    {
        return "rootDel";
    }

}
