package com.springboot.stumanage.controller;

import com.springboot.stumanage.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeaStuffController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SCRepository scRepository;

    @RequestMapping(value = "/teacher/find")
    public List<SC> func1(@ModelAttribute User user)
    {
        //在Course表中 通过老师名 得到课程名
        String teacherName = user.getUsername();
        List<Course> courseList = courseRepository.findByTeachername(teacherName);
        Course course = courseList.get(0);
        String courseName = course.getCoursename();

        //在SC表中 通过课程名 得到选该课程的所有信息
        return scRepository.findByCoursename(courseName);
    }

    @RequestMapping(value = "/teacher/modify/success")
    public String func2(@ModelAttribute SC sc)
    {
        String courseName = sc.getCoursename();
        String stuName = sc.getStuname();
        String grade = sc.getGrade();

        //如果学生不存在，显示错误信息
        List<Object> stuList = scRepository.findAllStuname();
        if(stuList.contains(stuName) == false)
        {
            return "Student not exist";
        }
        //如果学生没有选这门课，显示错误信息
        List<SC> scList = scRepository.findByStuname(stuName);
        int flag = 0;
        for(int i=0;i<scList.size();i++)
        {
            if(scList.get(i).getCoursename().equals(courseName)){
                flag = 1;
            }
        }
        if(flag == 0)
        {
            return "The student has not chose the course";
        }

        //找到id 去掉原记录 再增加新记录
        List<SC> scList1 = scRepository.findByCoursenameAndStuname(courseName,stuName);
        SC sc1 = scList1.get(0);
        scRepository.deleteById(sc1.getId());
        scRepository.save(sc);
        return "success";
    }
}
