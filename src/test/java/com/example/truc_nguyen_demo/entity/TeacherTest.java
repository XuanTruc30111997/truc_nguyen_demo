package com.example.truc_nguyen_demo.entity;

import com.example.truc_nguyen_demo.model.Entity.Class;
import com.example.truc_nguyen_demo.model.Entity.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert.*;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
public class TeacherTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherTest.class);

    private static Teacher teacher = new Teacher();
    private static final Set<Class> CLASSES = Mockito.any();

    @Test
    public void setClassesTest() {
        LOGGER.info("Start test >>> classTest()");

        teacher.setClasses(CLASSES);

        Assert.assertEquals(teacher.getClasses(), CLASSES);

        LOGGER.info("End test >>> classTest()");
    }

    @Test
    public void addClassTest()
    {
        LOGGER.info("Start test >>> addClassTest()");

        Class expectedClass = new Class();
        teacher.addClass(expectedClass);

        Optional<Class> actualClass = teacher.getClasses().stream().findFirst();

        Assert.assertNotNull(actualClass);
        Assert.assertEquals(expectedClass, actualClass.get());

        LOGGER.info("End test >>> addClassTest()");
    }

}
