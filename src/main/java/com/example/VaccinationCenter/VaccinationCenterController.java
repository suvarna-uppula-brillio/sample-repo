package com.example.VaccinationCenter;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

//    Logger logger = (Logger) LoggerFactory.getLogger(VaccinationCenterController.class);

    @Autowired
    VaccinationCenterRepo vacRepo;

    @Autowired
    Environment environment;

    @Autowired
    CitizenServiceProxy citizenServiceProxy;

    @PostMapping("/add")
    public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter vacCenter)
    {
        VaccinationCenter vc = vacRepo.save(vacCenter);
        return new ResponseEntity<>(vc, HttpStatus.OK);
    }

    @GetMapping("/getCenterDetails/{id}")
    @Retry(name = "citizen-api", fallbackMethod = "handleCitizenDowntime")
    public ResponseEntity<RequiredResponse> getAllDetailsByCenterId(@PathVariable int id)
    {
        System.out.println("getAllDetailsByCenterId");
        System.out.println(environment.getProperty("server.port"));
        RequiredResponse response = new RequiredResponse();
        response.setPort(environment.getProperty("server.port"));
        VaccinationCenter vc = vacRepo.findById(id).get();
        response.setVacCenter(vc);

        List<Citizen> citizensEntity = new RestTemplate().getForObject("http://localhost:8081/citizen/get/{centerId}",List.class, id);
        response.setCitizens(citizensEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getCenterDetailsFeign/{id}")
    public ResponseEntity<RequiredResponse> getAllDetailsByCenterIdFeign(@PathVariable int id)
    {
        RequiredResponse response = new RequiredResponse();
        response.setPort(environment.getProperty("server.port"));
        VaccinationCenter vc = vacRepo.findById(id).get();
        response.setVacCenter(vc);

        List<Citizen> citizens = citizenServiceProxy.getByVaccinationCenterId(id);
        response.setCitizens(citizens);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<String> handleCitizenDowntime(Exception e)
    {
        return new ResponseEntity<>("citizen api is down", HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<VaccinationCenter> getVaccinationCenter(@PathVariable int id)
    {
        VaccinationCenter vc = vacRepo.findById(id).get();
        return new ResponseEntity<>(vc, HttpStatus.OK);
    }

}
