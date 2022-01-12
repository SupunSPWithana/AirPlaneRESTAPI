package com.adl.springbootapi.service;

import com.adl.springbootapi.entity.AirPlane;
import com.adl.springbootapi.repository.AirPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AirPlaneService {

    private final AirPlaneRepository airPlaneRepository;

    @Autowired
    public AirPlaneService(AirPlaneRepository airPlaneRepository) {
        this.airPlaneRepository = airPlaneRepository;
    }

    public List<AirPlane> getAirPlanes(){
        return airPlaneRepository.findAll();
    }

    public List<Object> getAirPlanesByDate(LocalDate date){
        return airPlaneRepository.findAirPlanesByDate(date);
    }

    public List<Object> getAirPlanesByDepartureAndDestination(String departure, String destination){
        return airPlaneRepository.findAirPlanesByDepartureAndDestination(departure, destination);
    }

    public void addNewAirPlane(AirPlane airPlane){
        // Assumed airplane name is also unique.
        Optional<AirPlane> airPlaneOptional = airPlaneRepository.findAirPlaneByName(airPlane.getName());
        if (airPlaneOptional.isPresent()) {
            // Can use a Custom Exception.
            throw new IllegalStateException("AirPlane with the name " + airPlane.getName() + " already exists!");
        }
        airPlaneRepository.save(airPlane);

    }

    @Transactional
    public void updateAirPlane(Long airPlaneId, LocalDate date, LocalDateTime time) {
        AirPlane airPlane = airPlaneRepository.findById(airPlaneId).orElseThrow(() ->
                new IllegalStateException("AirPlane with airPlaneId : " + airPlaneId + " doesn't exists!"));

        if (date != null && !Objects.equals(airPlane.getDate(), date)) {
            airPlane.setDate(date);
        }
        if (time != null && !Objects.equals(airPlane.getTime(), time)) {
            airPlane.setTime(time);
        }

    }

    public void deleteAirPlane(Long airPlaneId) {
        boolean exists = airPlaneRepository.existsById(airPlaneId);
        if (!exists) {
            throw new IllegalStateException("AirPlane with airPlaneId : " + airPlaneId + " doesn't exists!");
        }
        airPlaneRepository.deleteById(airPlaneId);
    }

}