package com.Hackthon.SnowHack.Dto;



import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


public class StudentRequestDTO {

    @NotNull
    private Long userId;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String branch;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double cgpa;

    @Min(1)
    @Max(5)
    private Integer year;
    // Getters and Setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
}
