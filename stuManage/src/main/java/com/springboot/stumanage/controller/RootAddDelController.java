package com.springboot.stumanage.controller;

import com.springboot.stumanage.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RootAddDelController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SCRepository scRepository;

    private Course course = new Course();

    @RequestMapping("root/func/add/success")
    public String addUser(User user){
        userRepository.save(user);
        //如果是老师 Course表中也新加入一条记录
        if(user.getTab() == 1){
            course.setCoursename(user.getInfo());
            course.setTeachername(user.getUsername());
            courseRepository.save(course);
        }
        return "success";
    }

    @RequestMapping("root/func/del/success")
    public String delUser(User user){
        if(user.getTab() == 1)
        {
            //根据名字得到user1和course1 对老师来说均为一条记录
            List<User> userList = userRepository.findByusername(user.getUsername());
            User user1 = userList.get(0);
            List<Course> courseList = courseRepository.findByTeachername(user.getUsername());
            Course course1 = courseList.get(0);
            //根据id分别删除对应的一条记录
            userRepository.deleteById(user1.getId());
            courseRepository.deleteById(course1.getId());
        }
        else if(user.getTab() == 2)
        {
            //根据名字得到一条记录user1 多条记录sc
            List<User> userList = userRepository.findByusername(user.getUsername());
            User user1 = userList.get(0);
            List<SC> scList = scRepository.findByStuname(user.getUsername());
            //删除user表中一条记录
            userRepository.deleteById(user1.getId());
            //删除sc表中多条记录
            for(int i=0;i<scList.size();i++)
            {
                SC sc1 = scList.get(i);
                scRepository.deleteById(sc1.getId());
            }
        }
        return "success";
    }

    @RequestMapping("root/func/find")
    public List<User> findAllUser(){
        return (List<User>) userRepository.findAll();
    }
}
