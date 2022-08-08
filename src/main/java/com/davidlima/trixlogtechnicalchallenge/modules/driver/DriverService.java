package com.davidlima.trixlogtechnicalchallenge.modules.driver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.davidlima.trixlogtechnicalchallenge.exception.ConflictException;
import com.davidlima.trixlogtechnicalchallenge.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DriverService {
    
    DriverRepository driverRepository;

    public Driver save(DriverForm form) {
        driverRepository
            .findByLicenseNumber(form.licenseNumber())
            .ifPresent(driver -> {
                throw new ConflictException("License plate already registered");
            });

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
                .orElseThrow(() -> new NotFoundException("Driver not found"));
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findAndUpdate(Long id, DriverForm form) {
        return driverRepository
                .findById(id)
                .map(driver -> {
                    driverRepository
                        .findByLicenseNumber(form.licenseNumber())
                        .ifPresent(arg -> {
                            throw new ConflictException("License plate already registered");
                        });
                    driver.setName(form.name());
                    driver.setLicenseNumber(form.licenseNumber());
                    return driverRepository.save(driver);
                })
                .orElseThrow(() -> new NotFoundException("Driver not found"));
    }

    public void deleteById(Long id) {
        driverRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Driver not found"));

        driverRepository.deleteById(id);
    }

}
