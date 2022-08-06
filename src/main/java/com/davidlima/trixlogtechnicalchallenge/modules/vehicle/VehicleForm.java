package com.davidlima.trixlogtechnicalchallenge.modules.vehicle;

import java.time.LocalDate;

public record VehicleForm(
    String licensePlate,
    String chassis,
    String renavam,
    Integer modelYear,
    Integer fabricationYear,
    String color,
    String state,
    LocalDate acquisitionDate,
    Long driverId
) {}
