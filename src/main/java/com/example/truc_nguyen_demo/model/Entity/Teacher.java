package com.example.truc_nguyen_demo.model.Entity;

import com.example.truc_nguyen_demo.model.People;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacher")
@EntityListeners(AuditingEntityListener.class)
public class Teacher extends People {

    @ManyToMany(mappedBy = "teachers")
    @SuppressWarnings({"deprecation"})
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private Set<Class> classes = new HashSet<>();

    public Set<Class> getClasses()
    {
        return null;
    }

    public Set<Class> ahihi()
    {
        return classes;
    }

    public void setClasses(Set<Class> classes)
    {
        this.classes = classes;
    }

    public void addClass(Class clss)
    {
        this.classes.add(clss);
    }

    public void removeClass(Class clss)
    {
        this.classes.remove(clss);
        clss.getTeachers().remove(this);
    }

    public void removeClassAhihi()
    {
        for(Class clss : new HashSet<>(this.classes))
        {
            clss.getTeachers().remove(this);
        }
    }
}
