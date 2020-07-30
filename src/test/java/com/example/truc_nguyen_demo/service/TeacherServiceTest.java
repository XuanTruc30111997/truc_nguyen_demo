package com.example.truc_nguyen_demo.service;

import com.example.truc_nguyen_demo.entity.TeacherTest;
import com.example.truc_nguyen_demo.model.Entity.Teacher;
import com.example.truc_nguyen_demo.repository.ITeacherRepository;
import com.example.truc_nguyen_demo.service.teachService.ITeachService;
import com.example.truc_nguyen_demo.service.teacherService.ITeacherService;
import com.example.truc_nguyen_demo.service.teacherService.TeacherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
public class TeacherServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceTest.class);

    private static List<Teacher> teachers = new ArrayList<Teacher>();


    @Before
    public void setup()
    {
        Teacher teacher = new Teacher();
        teachers.add(teacher);
    }


    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public ITeacherRepository teacherRepository() {
            return Mockito.mock(ITeacherRepository.class);
        }

        @Bean
        public ITeacherService teacherService() {
            return Mockito.mock(TeacherService.class);
        }
    }

    @MockBean
    ITeacherRepository teacherRepository;

    @MockBean
    ITeacherService teacherService;

    @Test
    public void findTeacherByFirstName_giveValidFirstName_Success()
    {
        LOGGER.info("Start test >>> findTeacherByFirstName_giveValidFirstName_Success");
        Set<String> firstNames = new HashSet<>();
        firstNames.add("Truc");

        Teacher teacher = new Teacher();
        List<Teacher> expectedTeachers = new ArrayList<>();
        teachers.add(teacher);

        Mockito.when(teacherService.findTeacherByFirstNames(firstNames)).thenReturn(expectedTeachers);

        List<Teacher> actualTeachers = teacherService.findTeacherByFirstNames(firstNames);

        Assert.assertEquals(expectedTeachers, actualTeachers);

        LOGGER.info("End test >>> findTeacherByFirstName_giveValidFirstName_Success");

    }

}
