package com.example.truc_nguyen_demo.controller;

import com.example.truc_nguyen_demo.model.Entity.Class;
import com.example.truc_nguyen_demo.model.Teach;
import com.example.truc_nguyen_demo.service.teachService.TeachService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teach")
@Api(tags = "Teach API")
public class TeachController {

    @Autowired
    TeachService teachService;

    @PostMapping("")
    public ResponseEntity<Class> teach(@RequestBody Teach teach)
    {
        return new ResponseEntity<>(teachService.teach(teach), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Class> delete(@RequestBody Teach teach)
    {
        return new ResponseEntity<>(teachService.deleteTeach(teach), HttpStatus.OK);
    }
}
