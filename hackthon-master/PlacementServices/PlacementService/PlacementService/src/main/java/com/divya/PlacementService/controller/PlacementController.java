package com.divya.PlacementService.controller;

import com.divya.PlacementService.dto.CompanyDTO;
import com.divya.PlacementService.dto.UserResponse;
import com.divya.PlacementService.entity.Application;
import com.divya.PlacementService.entity.ApplicationStatus;
import com.divya.PlacementService.entity.RoundStatus;
import com.divya.PlacementService.feign.AuthClient;
import com.divya.PlacementService.feign.CompanyFeignClient;
import com.divya.PlacementService.repo.ApplicationRepository;
import com.divya.PlacementService.service.PlacementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/placements")
@RequiredArgsConstructor
public class PlacementController {
    @Autowired
    private PlacementService placementService;
    @Autowired
    private AuthClient authClient;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired

    private CompanyFeignClient companyFeignClient;

    // STUDENT APPLY
    @PostMapping("/apply/{companyId}")
    public void apply(Long studentId, Long companyId) {

        // 🔍 validate company
        companyFeignClient.getCompanyById(companyId);

        Application app = new Application();
        app.setStudentId(studentId);
        app.setCompanyId(companyId);
        app.setStatus(ApplicationStatus.APPLIED);
        app.setAppliedAt(LocalDate.now().atStartOfDay());

        applicationRepository.save(app);
    }


    // ADMIN ADD ROUND
    @PostMapping("/add-round/{applicationId}")
    public void addRound(
            @RequestHeader("Authorization") String token,
            @PathVariable Long applicationId,
            @RequestParam String roundName) {

        UserResponse user = authClient.validate(token);
        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Access Denied");
        }
        placementService.addRound(applicationId, roundName);
    }

    // ADMIN UPDATE ROUND STATUS
    @PutMapping("/update-round/{roundId}")
    public void updateRound(
            @RequestHeader("Authorization") String token,
            @PathVariable Long roundId,
            @RequestParam RoundStatus status) {

        UserResponse user = authClient.validate(token);
        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Access Denied");
        }
        placementService.updateRoundStatus(roundId, status);
    }

    // ADMIN MARK PLACED
    @PutMapping("/placed/{applicationId}")
    public void placed(
            @RequestHeader("Authorization") String token,
            @PathVariable Long applicationId) {

        UserResponse user = authClient.validate(token);
        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Access Denied");
        }
        placementService.markPlaced(applicationId);
    }

    // STUDENT VIEW STATUS
    @GetMapping("/my")
    public List<Application> myApplications(
            @RequestHeader("Authorization") String token) {

        UserResponse user = authClient.validate(token);
        return placementService.studentApplications(user.getUserId());
    }
    @GetMapping("/companies")
    public List<CompanyDTO> companiesForPlacement() {
        return companyFeignClient.getAllCompanies();
    }

}
