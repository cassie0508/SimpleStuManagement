package com.springboot.stumanage.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {
    public List<Course> findByTeachername(String name);

    @Query("select coursename from Course")
    public List<Object> findAllCoursename();
}
