package com.example.truc_nguyen_demo.service;

import com.example.truc_nguyen_demo.exception.ArgumentNotValidException;
import com.example.truc_nguyen_demo.model.Entity.Student;
import com.example.truc_nguyen_demo.model.StudentEdit;
import com.example.truc_nguyen_demo.repository.IStudentRepository;
import com.example.truc_nguyen_demo.service.studentService.IStudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @Mock
    IStudentRepository studentRepository;

    @Mock
    IStudentService studentService;

    @Test(expected = ArgumentNotValidException.class)
    public void createStudent_InvalidFirstName_ThrowArgumentNotValidException()
    {
        StudentEdit student = new StudentEdit();
        student.setFirstName(null);

        Mockito.doThrow(new ArgumentNotValidException("Not valid"))
                .when(studentService)
                .createStudent(student);

        Assert.assertEquals(studentService.createStudent(student), student);

        Mockito.verify(studentService.createStudent(student));

    }

}
