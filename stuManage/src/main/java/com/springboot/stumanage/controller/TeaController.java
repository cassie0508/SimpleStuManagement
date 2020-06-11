package com.springboot.stumanage.controller;

import com.springboot.stumanage.entity.Course;
import com.springboot.stumanage.entity.CourseRepository;
import com.springboot.stumanage.entity.User;
import com.springboot.stumanage.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TeaController {
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/teacher/modify", method = RequestMethod.POST)
    public String func1(@ModelAttribute User user)
    {
        //根据老师名得到课程名
        String teacherName = user.getUsername();
        List<Course> courseList = courseRepository.findByTeachername(teacherName);
        Course course = courseList.get(0);
        String courseName = course.getCoursename();
        //修改user传参
        user.setUsername(courseName);
        return "teaModify";
    }
}
