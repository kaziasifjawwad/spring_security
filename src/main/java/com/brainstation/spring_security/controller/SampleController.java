package com.brainstation.spring_security.controller;

import com.brainstation.spring_security.Models.Student;
import com.brainstation.spring_security.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class SampleController {

    public StudentServices studentServices;

    @Autowired
    public void setServices(StudentServices services) {
        this.studentServices = services;
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent( @RequestBody Student student) {
        try {
            return new ResponseEntity<>(this.studentServices.saveStudent(student), HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(
                this.studentServices.getALlStudents(),HttpStatus.OK
        );
    }

}
