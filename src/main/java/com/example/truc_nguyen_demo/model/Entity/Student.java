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

    public Class getClss()
    {
        return clss;
    }

    public void setClss(Class clss)
    {
        this.clss = clss;
    }
}
