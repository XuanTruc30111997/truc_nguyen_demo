package com.example.truc_nguyen_demo.model.Entity;

import com.example.truc_nguyen_demo.model.People;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "student")
@EntityListeners(AuditingEntityListener.class)
public class Student extends People {
    @ManyToOne
    @JoinColumn(name = "CLASS_ID", nullable = false)
    private Class clss;

    public Student(String firstName, String lastName, int age)
    {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
    }

    public Student()
    {

    }

    public Class getClss()
    {
        return clss;
    }

    public void setClss(Class clss)
    {
        this.clss = clss;
    }
}
