package com.example.VaccinationCenter;

import org.hibernate.cfg.Environment;

import java.util.List;

public class RequiredResponse {
    VaccinationCenter vacCenter;
    List<Citizen> citizens;
    String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public VaccinationCenter getVacCenter() {
        return vacCenter;
    }

    public void setVacCenter(VaccinationCenter vacCenter) {
        this.vacCenter = vacCenter;
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(List<Citizen> citizens) {
        this.citizens = citizens;
    }
}
