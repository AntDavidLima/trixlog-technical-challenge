package com.davidlima.trixlogtechnicalchallenge.modules.vehicle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

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
    public Vehicle create(@RequestBody VehicleForm form) {
        
        return vehicleService.save(form);

    }

    @GetMapping
    public List<Vehicle> index() {

        return vehicleService.findAll();

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
    public Vehicle update(@RequestParam Long id, @RequestBody VehicleForm form) {

        return vehicleService.findAndUpdate(id, form);

    }
    
    
}
