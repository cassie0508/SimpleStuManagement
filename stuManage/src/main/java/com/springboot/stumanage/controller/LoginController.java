package com.springboot.stumanage.controller;

import com.springboot.stumanage.entity.User;
import com.springboot.stumanage.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

@Controller
public class LoginController {
    //加@Autowired属性就可以实现接口自动的实例化了
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/index", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String signin(@ModelAttribute User user) {
        String username = user.getUsername();
        List<User> userList = userRepository.findByusername(username);
        if (userList.size() != 0) {
            User user1 = userList.get(0);
            switch (user1.getTab()) {
                case 0:
                    //user为输入的，user1为查表得到的
                    if (user1.getPassword().equals(user.getPassword())) {
                        return "rootFunc";
                    } else {
                        break;
                    }
                case 1:
                    if (user1.getPassword().equals(user.getPassword())) {
                        return "teaFunc";
                    } else {
                        break;
                    }
                case 2:
                    if (user1.getPassword().equals(user.getPassword())) {
                        //System.out.println("in login "+user);
                        return "stuFunc";
                    } else {
                        break;
                    }
                default:
                    break;
            }
        }
        return "index";
    }
}
