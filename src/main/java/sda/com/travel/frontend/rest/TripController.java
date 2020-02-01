package sda.com.travel.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.com.travel.bussiness.service.TripService;
import sda.com.travel.frontend.dto.TripDTO;

import java.util.List;

@RestController
@RequestMapping("/trip/")
public class TripController {
    @Autowired
    TripService tripService;

    @PostMapping(path = "insertTrip", consumes = "application/json")
    public ResponseEntity insertTrip(@RequestBody TripDTO tripDTO){
        tripService.insertTrip(tripDTO);
        return ResponseEntity.ok("Succes! The Trip was inserted in DB!");
    }

    @GetMapping(path = "findTripByHotelName/{hotelName}")
    public List<TripDTO> findTripByHotelName(@PathVariable String hotelName){
        List<TripDTO> tripDTOSList= tripService.findTripByHotelName(hotelName);
        return tripDTOSList;
    }

    @GetMapping(path = "findTrip", consumes = "application/json")
    public List<TripDTO> findTrip(@RequestBody TripDTO tripDTO){
        List<TripDTO> tripDTOList = tripService.findTrip(tripDTO);
        return tripDTOList;
    }
}
