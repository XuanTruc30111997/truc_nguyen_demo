package com.example.truc_nguyen_demo.service.studentService;

import com.example.truc_nguyen_demo.exception.ArgumentNotValidException;
import com.example.truc_nguyen_demo.exception.ResourceNotFoundException;
import com.example.truc_nguyen_demo.model.Entity.Class;
import com.example.truc_nguyen_demo.model.Entity.Student;
import com.example.truc_nguyen_demo.model.StudentEdit;
import com.example.truc_nguyen_demo.repository.IClassRepository;
import com.example.truc_nguyen_demo.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    IClassRepository classRepository;

    public Student createStudent(StudentEdit student)
    {
        if(student.getClassId() == null)
        {
            throw new ArgumentNotValidException("Class ID Not valid");
        }

        Optional<Class> myClass = classRepository.findById(student.getClassId());
        if(!myClass.isPresent())
        {
            throw new ResourceNotFoundException("Class not found");
        }

        if(student.getFirstName().isBlank() || student.getLastName().isBlank())
        {
            throw new ArgumentNotValidException("Not valid");
        }

        Student myStudent = new Student();
        myStudent.setFirstName(student.getFirstName());
        myStudent.setLastName(student.getLastName());
        myStudent.setAge(student.getAge());
        myStudent.setClss(myClass.get());

        return studentRepository.save(myStudent);
    }
}
