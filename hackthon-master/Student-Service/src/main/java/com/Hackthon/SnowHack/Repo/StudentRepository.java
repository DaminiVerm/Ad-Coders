package com.Hackthon.SnowHack.Repo;

import com.Hackthon.SnowHack.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserId(Long userId);

    List<Student> findByPlacedStatus(Boolean placedStatus);

    Optional<Student> findByEmail(String email);
}
