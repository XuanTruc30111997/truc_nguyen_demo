package com.example.truc_nguyen_demo.controller;

import com.example.truc_nguyen_demo.exception.ArgumentNotValidException;
import com.example.truc_nguyen_demo.exception.ResourceNotFoundException;
import com.example.truc_nguyen_demo.model.Entity.Student;
import com.example.truc_nguyen_demo.model.StudentEdit;
import com.example.truc_nguyen_demo.repository.IStudentRepository;
import com.example.truc_nguyen_demo.service.studentService.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@Api(tags = "Student API")
public class StudentController {
    @Autowired
    IStudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @GetMapping("")
    @ApiOperation("Get all students")
    public ResponseEntity<List<Student>> all()
    {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get student by id")
    public ResponseEntity<Student> getById(@PathVariable(value = "id") Long id)
    {
        Optional<Student> myStudent = studentRepository.findById(id);

        if(!myStudent.isPresent())
        {
            throw new ResourceNotFoundException("Student not found");
        }

        return new ResponseEntity<>(myStudent.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@Valid @RequestBody StudentEdit student)
    {
        Student myStudent = studentService.createStudent(student);
        return new ResponseEntity<>(myStudent, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Student> update(@Valid @RequestBody Student student)
    {
        Optional<Student> myStudent = studentRepository.findById(student.getId());
        if(!myStudent.isPresent())
        {
            throw new ResourceNotFoundException("Student not found");
        }

        if(student.getFirstName().isBlank() || student.getLastName().isBlank())
        {
            throw new ArgumentNotValidException("Not valid");
        }

        return new ResponseEntity<>(studentRepository.save(student) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id)
    {
        Optional<Student> myStudent = studentRepository.findById(id);
        if(!myStudent.isPresent())
        {
            throw new ResourceNotFoundException("Student not found");
        }

        studentRepository.delete(myStudent.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
