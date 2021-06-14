package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Student> getList() {
        return studentRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getDetail(@PathVariable int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Student save(@RequestBody Student student) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        student.setStatus(1);
        student.setCreatedAt(formatter.format(date));
        student.setUpdatedAt(formatter.format(date));
        return studentRepository.save(student);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Student update(@PathVariable int id, @RequestBody Student student) {
        Student existStudent = studentRepository.findById(id).orElse(null);
        if (existStudent != null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            student.setUpdatedAt(formatter.format(date));
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable int id) {
        Student existStudent = studentRepository.findById(id).orElse(null);
        if (existStudent != null){
            studentRepository.delete(existStudent);
            return true;
        } else {
            return false;
        }
    }
}
