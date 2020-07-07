package com.example.truc_nguyen_demo.service.teacherService;

import com.example.truc_nguyen_demo.model.Entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ITecherService {
    void deleteTeacher(Long id);
    List<Teacher> findTeacherByFirstNames(Set<String> firstName);
}
