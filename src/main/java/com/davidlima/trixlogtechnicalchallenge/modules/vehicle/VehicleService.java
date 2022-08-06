package com.davidlima.trixlogtechnicalchallenge.modules.vehicle;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle findAndUpdate(Long id, VehicleForm form) {
        Driver driver = driverService.findById(form.driverId());

        return vehicleRepository
                .findById(id)
                .map(vehicle -> {
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
                .get();
    }

}
