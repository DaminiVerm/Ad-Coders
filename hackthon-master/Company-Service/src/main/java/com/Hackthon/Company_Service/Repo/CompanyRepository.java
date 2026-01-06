package com.Hackthon.Company_Service.Repo;

import com.Hackthon.Company_Service.dto.CompanyRequestDTO;
import com.Hackthon.Company_Service.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<CompanyRequestDTO> findByIsActiveTrue();
}
