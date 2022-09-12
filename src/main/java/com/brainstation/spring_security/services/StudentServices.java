package com.brainstation.spring_security.services;

import com.brainstation.spring_security.Models.Student;
import com.brainstation.spring_security.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServices {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        this.studentRepository.delete(student);
    }

    public Student updateStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Student findById(Integer id){
        Student student = this.studentRepository.findById(id).get();
        if (student == null) throw new NoSuchElementException();
        return student;
    }

    public List<Student> getALlStudents() {
        return this.studentRepository.findAll();
    }
}
