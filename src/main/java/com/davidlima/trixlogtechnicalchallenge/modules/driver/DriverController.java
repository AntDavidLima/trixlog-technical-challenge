package com.davidlima.trixlogtechnicalchallenge.modules.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    
    @Autowired
    DriverService driverService;
    
    @GetMapping
    public List<Driver> index() {
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public Driver show(@RequestParam Long id) {
        return driverService.findById(id);
    }

    @PostMapping
    public Driver create(@RequestBody DriverForm form) {
        return driverService.save(form);
    }

    @PutMapping
    public Driver update(@RequestParam Long id, @RequestBody DriverForm form) {
        return driverService.findAndUpdate(id, form);
    }

    @DeleteMapping
    public void destroy(@RequestParam Long id) {
        driverService.deleteById(id);
    }

}