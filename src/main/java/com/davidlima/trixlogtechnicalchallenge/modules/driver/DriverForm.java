package com.davidlima.trixlogtechnicalchallenge.modules.driver;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record DriverForm(
    @NotBlank(message = "Name is required")
    String name,

    @Pattern(regexp = "[0-9]{11}", message = "Invalid license number")
    @NotNull(message = "License number is required")
    String licenseNumber
) {}