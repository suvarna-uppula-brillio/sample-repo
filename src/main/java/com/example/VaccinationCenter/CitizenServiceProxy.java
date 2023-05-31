package com.example.VaccinationCenter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value="citizen-service")
public interface CitizenServiceProxy {

    @RequestMapping("/citizen/get/{centerId}")
    public List<Citizen> getByVaccinationCenterId(@PathVariable int centerId);
}
