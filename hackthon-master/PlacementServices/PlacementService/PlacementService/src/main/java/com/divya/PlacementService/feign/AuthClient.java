package com.divya.PlacementService.feign;

import com.divya.PlacementService.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "HackPro", url = "http://localhost:9090")
public interface AuthClient {

    @GetMapping("/auth/validate")
    UserResponse validate(@RequestHeader("Authorization") String token);
}