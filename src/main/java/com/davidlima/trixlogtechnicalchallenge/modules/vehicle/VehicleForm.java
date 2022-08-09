package com.davidlima.trixlogtechnicalchallenge.modules.vehicle;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

public record VehicleForm(
    @Pattern(regexp = "[A-Za-z]{3}[0-9][A-Za-z0-9][0-9]{2}", message = "Invalid format")
    @NotNull
    String licensePlate,

    @NotBlank(message = "Chassis is required")
    String chassis,

    @NotBlank(message = "Renavam is required")
    String renavam,

    @NotNull(message = "Model year is required")
    Integer modelYear,

    @NotNull(message = "Fabrication year is required")
    Integer fabricationYear,

    @NotBlank(message = "Color is required")
    String color,

    @NotBlank(message = "State is required")
    State state,

    @NotNull(message = "Acquisition year is required")
    @PastOrPresent(message = "Acquisition date can not be in the future, or can it be?")
    LocalDate acquisitionDate,

    @NotNull(message = "Driver is required")
    Long driverId
) {}
