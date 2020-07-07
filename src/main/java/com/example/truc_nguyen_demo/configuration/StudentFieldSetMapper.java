package com.example.truc_nguyen_demo.configuration;

import com.example.truc_nguyen_demo.model.Entity.Student;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class StudentFieldSetMapper implements FieldSetMapper<Student> {
    @Override
    public Student mapFieldSet(FieldSet fieldSet) throws BindException {
        Student student = new Student();

        student.setFirstName(fieldSet.readString("firstName"));
        student.setLastName(fieldSet.readString("lastName"));
        student.setAge(fieldSet.readInt("age"));

        return student;
    }
}
