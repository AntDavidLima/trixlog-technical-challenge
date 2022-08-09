package com.davidlima.trixlogtechnicalchallenge.modules.vehicle;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.davidlima.trixlogtechnicalchallenge.modules.driver.Driver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false, unique = true)
    private String chassis;

    @Column(nullable = false, unique = true)
    private String renavam;

    @Column(nullable = false)
    private Integer modelYear;

    @Column(nullable = false)
    private Integer fabricationYear;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private State state;

    @Column(nullable = false)
    private LocalDate acquisitionDate;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

}