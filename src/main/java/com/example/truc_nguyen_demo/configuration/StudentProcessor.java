package com.example.truc_nguyen_demo.configuration;

import com.example.truc_nguyen_demo.model.Entity.Student;
import org.springframework.batch.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(final Student student) throws Exception {
        final String firstName = student.getFirstName();
        final String lastName = student.getLastName();
        final int age = student.getAge();

        final  Student processedStudent = new Student();
        processedStudent.setFirstName(firstName);
        processedStudent.setLastName(lastName);
        processedStudent.setAge(age);

        return processedStudent;
    }
}
