package com.Hackthon.SnowHack.Controller;

import com.Hackthon.SnowHack.Entity.Student;
import com.Hackthon.SnowHack.Dto.StudentRequestDTO;
import com.Hackthon.SnowHack.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins="*")
public class StudentController {

    @Autowired
    private StudentService service;

    // Create / Update Student
    @PostMapping("/profile")
    public Student saveProfile(@Valid @RequestBody StudentRequestDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setBranch(dto.getBranch());
        student.setCgpa(dto.getCgpa());
        student.setYear(dto.getYear());
        return service.createOrUpdate(student);
    }

    // Get profile by userId
    @GetMapping("/me/{userId}")
    public Student getProfile(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    // Get all students (admin)
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // Get placed students (admin)
    @GetMapping("/placed")
    public List<Student> getPlacedStudents() {
        return service.getPlacedStudents();
    }

    // Get not placed students (admin)
    @GetMapping("/not-placed")
    public List<Student> getNotPlacedStudents() {
        return service.getNotPlacedStudents();
    }

    // Mark student placed (admin / placement service)
    @PutMapping("/mark-placed/{id}")
    public Student markPlaced(@PathVariable Long id) {
        return service.markStudentPlaced(id);
    }
}
