package com.davidlima.trixlogtechnicalchallenge.modules.vehicle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.davidlima.trixlogtechnicalchallenge.exception.ConflictException;
import com.davidlima.trixlogtechnicalchallenge.exception.NotFoundException;
import com.davidlima.trixlogtechnicalchallenge.modules.driver.Driver;
import com.davidlima.trixlogtechnicalchallenge.modules.driver.DriverService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VehicleService {

    VehicleRepository vehicleRepository;
    DriverService driverService;

    public Vehicle save(VehicleForm form) {

        Driver driver = driverService.findById(form.driverId());

        return vehicleRepository.save(
            Vehicle
                .builder()
                .licensePlate(form.licensePlate())
                .chassis(form.chassis())
                .renavam(form.renavam())
                .modelYear(form.modelYear())
                .fabricationYear(form.fabricationYear())
                .color(form.color())
                .state(form.state())
                .acquisitionDate(form.acquisitionDate())
                .driver(driver)
                .build()
        );

    }

    public List<Vehicle> find(
        LocalDate startDate,
        LocalDate endDate,
        String licensePlate,
        State state,
        String driverName
    ) {
        if (startDate != null && startDate.isAfter(endDate)) {
            throw new ConflictException("The start date is after the end date");
        }

        if (startDate != null && startDate.isAfter(LocalDate.now())) {
            throw new ConflictException("The start date is in the future");
        }

        return vehicleRepository.find(
            startDate,
            endDate,
            licensePlate,
            state,
            driverName
        );
    }

    public Vehicle findById(Long id) {
        return vehicleRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Vehicle not found"));
    }

    public void deleteById(Long id) {
        vehicleRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        vehicleRepository.deleteById(id);
    }

    public Vehicle findAndUpdate(Long id, VehicleForm form) {

        return vehicleRepository
                .findById(id)
                .map(vehicle -> {
                    Driver driver = driverService.findById(form.driverId());

                    vehicle.setAcquisitionDate(form.acquisitionDate());
                    vehicle.setChassis(form.chassis());
                    vehicle.setColor(form.color());
                    vehicle.setFabricationYear(form.fabricationYear());
                    vehicle.setLicensePlate(form.licensePlate());
                    vehicle.setModelYear(form.modelYear());
                    vehicle.setRenavam(form.renavam());
                    vehicle.setState(form.state());
                    vehicle.setDriver(driver);
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
    }

}
