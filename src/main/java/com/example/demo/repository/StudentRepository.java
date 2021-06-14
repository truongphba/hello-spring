package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.fullName like %:keyword% or s.email like %:keyword%")
    Page<Student> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
