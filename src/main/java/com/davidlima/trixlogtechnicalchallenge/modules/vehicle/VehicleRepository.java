package com.davidlima.trixlogtechnicalchallenge.modules.vehicle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(
        " select v from Vehicle v " +
        " where (cast(:startDate as date) is null or v.acquisitionDate >= :startDate) " +
        " and (cast(:endDate as date) is null or v.acquisitionDate <= :endDate) " +
        " and (:licensePlate is null or lower(v.licensePlate) like lower('%' || :licensePlate || '%')) " +
        " and (:state is null or lower(v.state) = lower(:state)) " +
        " and (:driverName is null or lower(v.driver.name) like lower('%' || :driverName || '%')) "
    )
    List<Vehicle> find(
        LocalDate startDate,
        LocalDate endDate,
        String licensePlate,
        State state,
        String driverName
    );

}
