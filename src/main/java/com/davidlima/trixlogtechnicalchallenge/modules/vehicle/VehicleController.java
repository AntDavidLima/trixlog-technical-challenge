package com.davidlima.trixlogtechnicalchallenge.modules.vehicle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
    
    VehicleService vehicleService;

    @PostMapping
    public Vehicle create(@Valid @RequestBody VehicleForm form) {
        
        return vehicleService.save(form);

    }

    @GetMapping
    public List<Vehicle> index(
        @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
        @RequestParam(required = false) String licensePlate,
        @RequestParam(required = false) State state,
        @RequestParam(required = false) String driverName
    ) {

        return vehicleService.find(
            startDate,
            endDate,
            licensePlate,
            state,
            driverName
        );

    }

    @GetMapping("/{id}")
    public Vehicle show(@RequestParam Long id) {

        return vehicleService.findById(id);

    }

    @DeleteMapping("/{id}")
    public void destroy(@RequestParam Long id) {

        vehicleService.deleteById(id);

    }

    @PutMapping("/{id}")
    public Vehicle update(@RequestParam Long id, @Valid @RequestBody VehicleForm form) {

        return vehicleService.findAndUpdate(id, form);

    }
    
    
}
