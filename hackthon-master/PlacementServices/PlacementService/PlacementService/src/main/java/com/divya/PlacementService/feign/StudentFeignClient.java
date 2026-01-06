package com.divya.PlacementService.feign;

import com.divya.PlacementService.dto.StudentDTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "student-service",
        url = "http://localhost:8082"   // student service port
)
public interface StudentFeignClient {

    @GetMapping("/students/{id}")
    StudentDTo getStudentById(@PathVariable Long id);
}
