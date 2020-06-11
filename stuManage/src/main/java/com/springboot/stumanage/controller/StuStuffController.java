package com.springboot.stumanage.controller;

import com.springboot.stumanage.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StuStuffController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SCRepository scRepository;

    @RequestMapping("student/choose/success")
    public String chooseCourse(SC sc)
    {
        String coursename = sc.getCoursename();

        List<Object> cnameList = courseRepository.findAllCoursename();
        //System.out.println("All course name: "+cnameList);
        //如果课程不存在 跳转到错误信息视图
        boolean bool = cnameList.contains(coursename);
        if(bool == false){
            return "Course not exist";
        }

        //成绩初始化为空 插入一条sc记录
        sc.setGrade("");
        scRepository.save(sc);
        return "success";
    }

    @RequestMapping("student/find/success")
    public String findGrade(SC sc)
    {
        String courseName = sc.getCoursename();
        String stuName = sc.getStuname();
        List<SC> scList = scRepository.findByCoursenameAndStuname(courseName,stuName);
        SC sc1 = scList.get(0);
        return sc1.getGrade();
    }
}
