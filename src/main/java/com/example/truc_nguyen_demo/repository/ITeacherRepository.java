package com.example.truc_nguyen_demo.repository;

import com.example.truc_nguyen_demo.model.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT * FROM Teacher t WHERE t.first_name like %:firstName", nativeQuery = true)
    List<Teacher> getWithFirstNameContain(@Param("firstName") String firstName);

    @Query(value = "SELECT * FROM Teacher t ORDER BY first_name DESC", nativeQuery = true)
    List<Teacher> allTeacherByFirstNameDesc();
}
