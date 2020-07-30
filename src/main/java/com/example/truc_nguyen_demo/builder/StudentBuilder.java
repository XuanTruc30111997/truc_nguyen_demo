package com.example.truc_nguyen_demo.builder;

import com.example.truc_nguyen_demo.model.Entity.Student;

public class StudentBuilder {

    private Student student;

    public StudentBuilder()
    {
        student = new Student();
    }

    public StudentBuilder withFirstName(String firstName)
    {
        student.setFirstName(firstName);
        return this;
    }

    public StudentBuilder withLastName(String lastName)
    {
        student.setLastName(lastName);
        return this;
    }

    public StudentBuilder withAge(Integer age)
    {
        student.setAge(age);
        return this;
    }

    public Student build()
    {
        return student;
    }
}
