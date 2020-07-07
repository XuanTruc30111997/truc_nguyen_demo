package com.example.truc_nguyen_demo.controller;

import com.example.truc_nguyen_demo.exception.ArgumentNotValidException;
import com.example.truc_nguyen_demo.exception.ResourceNotFoundException;
import com.example.truc_nguyen_demo.model.Entity.Teacher;
import com.example.truc_nguyen_demo.model.Teach;
import com.example.truc_nguyen_demo.repository.ITeacherRepository;
import com.example.truc_nguyen_demo.service.teacherService.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/teachers")
@Api(tags = "Teacher API")
public class TeacherController {
    @Autowired
    ITeacherRepository teacherRepository;

    @Autowired
    TeacherService teacherService;

    @GetMapping("")
    public ResponseEntity<List<Teacher>> all()
    {
        return new ResponseEntity<>(teacherRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable(value = "id") Long id)
    {
        Optional<Teacher> myTeacher = teacherRepository.findById(id);

        if(!myTeacher.isPresent())
        {
            throw new ResourceNotFoundException("Student not found");
        }

        return new ResponseEntity<>(myTeacher.get(), HttpStatus.OK);
    }

    @GetMapping("/byFirstNames")
    public ResponseEntity<List<Teacher>> all(@RequestBody Set<String> firstNames)
    {
        return new ResponseEntity<>(teacherService.findTeacherByFirstNames(firstNames), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@Valid @RequestBody Teacher teacher)
    {
        if(teacher.getFirstName().isBlank() || teacher.getLastName().isBlank())
        {
            throw new ArgumentNotValidException("Not valid");
        }

        Teacher myTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(myTeacher, HttpStatus.OK);
    }

    @GetMapping("/usingQuery")
    public ResponseEntity<List<Teacher>> allByFirstNameDesc()
    {
        return new ResponseEntity<>(teacherRepository.allTeacherByFirstNameDesc(), HttpStatus.OK);
    }

    @GetMapping("/usingQueryContain")
    public ResponseEntity<List<Teacher>> getWithFirstNameContain(@RequestBody String firstName)
    {
        return new ResponseEntity<>(teacherRepository.getWithFirstNameContain(firstName), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Teacher> update(@Valid @RequestBody Teacher student)
    {
        Optional<Teacher> myTeacher = teacherRepository.findById(student.getId());
        if(!myTeacher.isPresent())
        {
            throw new ResourceNotFoundException("Student not found");
        }

        if(student.getFirstName().isBlank() || student.getLastName().isBlank())
        {
            throw new ArgumentNotValidException("Not valid");
        }

        return new ResponseEntity<>(teacherRepository.save(student) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id)
    {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
