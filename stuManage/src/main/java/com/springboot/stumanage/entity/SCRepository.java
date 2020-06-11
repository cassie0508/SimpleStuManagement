package com.springboot.stumanage.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SCRepository extends CrudRepository<SC, Long> {
    public List<SC> findByStuname(String name);

    public List<SC> findByCoursename(String name);

    public List<SC> findByCoursenameAndStuname(String courseName,String stuName);

    @Query("select stuname from SC")
    public List<Object> findAllStuname();
}