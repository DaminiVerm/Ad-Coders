package com.divya.PlacementService.feign;

import com.divya.PlacementService.dto.CompanyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Company-service", url = "http://localhost:8083")
public interface CompanyFeignClient {

    @GetMapping("/company/all")
    List<CompanyDTO> getAllCompanies();

    @GetMapping("/company/{id}")
    CompanyDTO getCompanyById(@PathVariable("id") Long id);


}
