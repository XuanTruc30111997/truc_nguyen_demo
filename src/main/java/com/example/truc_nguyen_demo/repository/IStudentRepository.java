package com.example.truc_nguyen_demo.repository;

import com.example.truc_nguyen_demo.model.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
}
