package com.brainstation.spring_security.repository;

import com.brainstation.spring_security.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Integer> {
}
