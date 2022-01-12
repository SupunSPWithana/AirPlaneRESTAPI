package com.adl.springbootapi.repository;

import com.adl.springbootapi.entity.AirPlane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AirPlaneRepository extends JpaRepository<AirPlane, Long> {

    /**
     * Finds AirPlane by using plane name.
     * @param name
     * @return  A Plane whose name is a match with the given name.
     */
    @Query("SELECT a FROM AirPlane a WHERE a.name = ?1")
    Optional<AirPlane> findAirPlaneByName(String name);

    /**
     * Find AirPlanes by using date.
     * @param date
     * @return  A list of Planes whose flying date is a match with the given date.
     */
    @Query("SELECT a FROM AirPlane a WHERE a.date = ?1")
    List<Object> findAirPlanesByDate(LocalDate date);

    /**
     * Find AirPlanes by using departure and destination.
     * @param departure
     * @param destination
     * @return  A list of Planes whose departure & destination match with the given departure & destination.
     */
    @Query("SELECT a FROM AirPlane a WHERE a.departure = ?1 AND a.destination = ?2")
    List<Object> findAirPlanesByDepartureAndDestination(String departure, String destination);

}