package com.Hackthon.Company_Service.Controller;

import com.Hackthon.Company_Service.dto.CompanyRequestDTO;
import com.Hackthon.Company_Service.entity.Company;
import com.Hackthon.Company_Service.Service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins="*")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // ✅ ADMIN uploads company
    @PostMapping
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
        Company savedCompany = companyService.addCompany(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}
