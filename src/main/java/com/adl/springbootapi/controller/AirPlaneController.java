package com.adl.springbootapi.controller;

import com.adl.springbootapi.entity.AirPlane;
import com.adl.springbootapi.service.AirPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/airplane")
public class AirPlaneController {

    private final AirPlaneService airPlaneService;

    @Autowired
    public AirPlaneController(AirPlaneService airPlaneService) {
        this.airPlaneService = airPlaneService;
    }

    // GET http://localhost:8080/api/v1/airplane
    @GetMapping
    public List<AirPlane> getAirPlanes(){
        return airPlaneService.getAirPlanes();
    }

    // GET http://localhost:8080/api/v1/airplane/date/2021-10-10
    @GetMapping(path = "/date/{date}")
    public List<Object> getAirPlanesByDate(@PathVariable("date")
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return airPlaneService.getAirPlanesByDate(date);
    }

    // GET http://localhost:8080/api/v1/airplane/departure_to_destination?departure=Colombo&destination=UK
    @GetMapping(path = "/departure_to_destination")
    public List<Object> getAirPlanesByDepartureAndDestination(@RequestParam(value = "departure") String departure,
                                                              @RequestParam(value = "destination") String destination){
        return airPlaneService.getAirPlanesByDepartureAndDestination(departure, destination);
    }

    /*
    POST http://localhost:8080/api/v1/airplane
    Content-Type: application/json
    {
            "name" : "Emirates",
            "departure" : "Colombo",
            "destination" : "Abu Dhabi",
            "date" : "2022-02-10",
            "time" : "2022-02-10T08:30:00",
            "seats" : 500
    }
    */
    @PostMapping
    public void registerNewAirPlane(@RequestBody AirPlane airPlane){
        airPlaneService.addNewAirPlane(airPlane);
    }

    // PUT http://localhost:8080/api/v1/airplane/1?date=2014-07-02&date=2014-07-02T11:00:00
    @PutMapping(path = "{airPlaneId}")
    public void updateAirPlane(@PathVariable("airPlaneId") Long airPlaneId,
        @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time){
        airPlaneService.updateAirPlane(airPlaneId, date, time);
    }

    // DELETE http://localhost:8080/api/v1/airplane/2
    @DeleteMapping(path = "{airPlaneId}")
    public void deleteAirPlane(@PathVariable("airPlaneId") Long airPlaneId){
        airPlaneService.deleteAirPlane(airPlaneId);
    }

}