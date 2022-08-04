package com.davidlima.trixlogtechnicalchallenge.modules.driver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    
    @GetMapping
    public Driver show() {
        Driver driver = new Driver(1L, "David Lima", "ABC1234");
        return driver;
    }

    @PostMapping
    public Driver create(@RequestBody Driver form) {
        return form;
    }
    
}
