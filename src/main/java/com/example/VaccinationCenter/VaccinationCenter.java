package com.example.VaccinationCenter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VaccinationCenter {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String centerName;
    private String centerAddress;

    public VaccinationCenter(int id, String centerName, String centerAddress) {
        super();
        this.id = id;
        this.centerName = centerName;
        this.centerAddress = centerAddress;
    }

    public VaccinationCenter() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }
}
