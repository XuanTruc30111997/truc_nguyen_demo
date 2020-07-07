package com.example.truc_nguyen_demo.repository;

import com.example.truc_nguyen_demo.model.Entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassRepository extends JpaRepository<Class, Long> {
}
