package com.davidlima.trixlogtechnicalchallenge.modules.driver;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DriverService {
    
    DriverRepository driverRepository;


    public Driver save(DriverForm form) {
        return driverRepository.save(
            Driver
                .builder()
                .name(form.name())
                .licenseNumber(form.licenseNumber())
                .build()
        );
    }

    public Driver findById(Long id) {
        return driverRepository
                .findById(id)
                .get();
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findAndUpdate(Long id, DriverForm form) {
        return driverRepository
                .findById(id)
                .map(driver -> {
                    driver.setName(form.name());
                    driver.setLicenseNumber(form.licenseNumber());
                    return driverRepository.save(driver);
                })
                .get();
    }

    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    } 

}
