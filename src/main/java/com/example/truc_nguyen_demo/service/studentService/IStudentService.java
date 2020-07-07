package com.example.truc_nguyen_demo.service.studentService;

import com.example.truc_nguyen_demo.model.Entity.Student;
import com.example.truc_nguyen_demo.model.StudentEdit;

public interface IStudentService {
    Student createStudent(StudentEdit student);
}
