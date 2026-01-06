package com.Hackthon.SnowHack.Service;

import com.Hackthon.SnowHack.Entity.Student;
import com.Hackthon.SnowHack.Repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // Create or Update student profile
    public Student createOrUpdate(Student student) {
        return repository.save(student);
    }

    // Get by userId
    public Student getByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Student not found!"));
    }

    // Get by email (optional)
    public Student getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found!"));
    }

    // Get all students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Get all placed students
    public List<Student> getPlacedStudents() {
        return repository.findByPlacedStatus(true);
    }

    // Get all not placed students
    public List<Student> getNotPlacedStudents() {
        return repository.findByPlacedStatus(false);
    }

    // Mark student as placed
    public Student markStudentPlaced(Long studentId) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found!"));
        student.setPlacedStatus(true);
        return repository.save(student);
    }

    // Eligibility check for company
    public boolean isEligibleForCompany(Student student, double companyMinCGPA) {
        return student.getCgpa() >= companyMinCGPA;
    }
}
