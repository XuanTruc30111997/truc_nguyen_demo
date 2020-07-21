package com.example.truc_nguyen_demo.model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "class")
@EntityListeners(AuditingEntityListener.class)
public class Class {
    @Id
    private Long id;

    @NotBlank(message = "Class Name is required")
    @Column(name = "NAME", unique = true)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Class_Teacher",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    @JsonManagedReference
    private Set<Teacher> teachers = new HashSet<>();

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Teacher> getTeachers()
    {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers)
    {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher)
    {
        this.teachers.add(teacher);
    }
}
