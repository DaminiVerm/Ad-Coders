package com.Hackthon.Company_Service.Service;

import com.Hackthon.Company_Service.Repo.CompanyRepository;
import com.Hackthon.Company_Service.dto.CompanyRequestDTO;
import com.Hackthon.Company_Service.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // ✅ Admin uploads company
    public Company addCompany(Company company) {
        company.setActive(true);
        return companyRepository.save(company);
    }
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
