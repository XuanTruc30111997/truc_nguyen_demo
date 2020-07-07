package com.example.truc_nguyen_demo.controller;

import com.example.truc_nguyen_demo.exception.ArgumentNotValidException;
import com.example.truc_nguyen_demo.exception.ResourceNotFoundException;
import com.example.truc_nguyen_demo.model.Entity.Class;
import com.example.truc_nguyen_demo.repository.IClassRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
@Api(tags = "Class API")
public class ClassController {
    @Autowired
    IClassRepository classRepository;

    @GetMapping("")
    public ResponseEntity<List<Class>> all()
    {
        return new ResponseEntity<>(classRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getById(@PathVariable(value = "id") Long id)
    {
        Optional<Class> myClass = classRepository.findById(id);
        if(!myClass.isPresent())
        {
            throw new ResourceNotFoundException("Class not found");
        }

        return new ResponseEntity<>(myClass.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Class> create(@Valid @RequestBody Class clss)
    {
        if(clss.getName().isBlank())
        {
            throw new ArgumentNotValidException("Name not valid");
        }

        Class myClass = classRepository.save(clss);
        return new ResponseEntity<>(myClass, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Class> update(@Valid @RequestBody Class clss)
    {
        Optional<Class> myClass = classRepository.findById(clss.getId());
        if(!myClass.isPresent())
        {
            throw new ResourceNotFoundException("Class not found");
        }

        if(clss.getName().isBlank())
        {
            throw new ArgumentNotValidException("Name not valid");
        }

        return new ResponseEntity<>(clss, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        Optional<Class> myClass = classRepository.findById(id);
        if(!myClass.isPresent())
        {
            throw new ResourceNotFoundException("Class not found");
        }

        classRepository.delete(myClass.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
