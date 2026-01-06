package com.divya.PlacementService.service;

import com.divya.PlacementService.dto.ApplicationStatusResponse;
import com.divya.PlacementService.dto.CompanyDTO;
import com.divya.PlacementService.entity.Application;
import com.divya.PlacementService.entity.ApplicationRound;
import com.divya.PlacementService.entity.ApplicationStatus;
import com.divya.PlacementService.entity.RoundStatus;
import com.divya.PlacementService.feign.CompanyFeignClient;
import com.divya.PlacementService.repo.ApplicationRepository;
import com.divya.PlacementService.repo.ApplicationRoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacementService {
    @Autowired
    private ApplicationRepository applicationRepo;
    @Autowired
    private ApplicationRoundRepository roundRepo;
    @Autowired
    private CompanyFeignClient companyFeignClient;

    public Application apply(Long studentId, Long companyId) {
        Application app = new Application();
        app.setStudentId(studentId);
        app.setCompanyId(companyId);
        app.setStatus(ApplicationStatus.APPLIED);
        return applicationRepo.save(app);
    }

    public void addRound(Long applicationId, String roundName) {
        ApplicationRound round = new ApplicationRound();
        round.setApplicationId(applicationId);
        round.setRoundName(roundName);
        round.setRoundStatus(RoundStatus.PENDING);
        roundRepo.save(round);

        Application app = applicationRepo.findById(applicationId).get();
        app.setStatus(ApplicationStatus.IN_PROGRESS);
        applicationRepo.save(app);
    }

    public void updateRoundStatus(Long roundId, RoundStatus status) {
        ApplicationRound round = roundRepo.findById(roundId).get();
        round.setRoundStatus(status);
        roundRepo.save(round);
    }

    public void markPlaced(Long applicationId) {
        Application app = applicationRepo.findById(applicationId).get();
        app.setStatus(ApplicationStatus.PLACED);
        applicationRepo.save(app);
    }

    public List<Application> studentApplications(Long studentId) {
        return applicationRepo.findByStudentId(studentId);
    }
    public List<ApplicationStatusResponse> getStudentStatus(Long studentId) {

        List<Application> apps = applicationRepo.findByStudentId(studentId);

        return apps.stream().map(app -> {

            CompanyDTO company = companyFeignClient.getCompanyById(app.getCompanyId());
            List<ApplicationRound> rounds =
                    roundRepo.findByApplicationId(app.getId());

            String currentRound = rounds.isEmpty()
                    ? "Not Started"
                    : rounds.get(rounds.size() - 1).getRoundName();

            ApplicationStatusResponse res = new ApplicationStatusResponse();
            res.setApplicationId(app.getId());
            res.setCompanyName(company.getName());
            res.setCurrentRound(currentRound);
            res.setStatus(app.getStatus());

            return res;

        }).toList();
    }

}
