package sda.com.travel.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.com.travel.bussiness.service.TripService;
import sda.com.travel.frontend.dto.TripDTO;

import java.sql.Date;
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

    @PostMapping(path = "buyTrip", consumes = "application/json")
    public ResponseEntity<String> buyTrip (@RequestBody TripDTO tripDTO){
        String response;
        try {
            response = tripService.buyTrip(tripDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error!---------------------------");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "findTripsByCountries/{countryName}")
    public ResponseEntity<List<TripDTO>> findTripsByCountries(@PathVariable String countryName){

        List<TripDTO> tripDTOSList = tripService.findTripsByCountries(countryName);
        return ResponseEntity.ok().body(tripDTOSList);
    }

    @GetMapping(path = "transferTripFieldsInTripDTOObject/{isPromoted}")
    public List<TripDTO> transferTripFieldsInTripDTOObject(@PathVariable boolean isPromoted){
        List<TripDTO> tripDTOSList = tripService.findTripIfIsPromoted(isPromoted);
        return tripDTOSList;
    }

    @GetMapping(path = "findTripByType/{type}")
    public List<TripDTO> findTripByType(@PathVariable String type){
        List<TripDTO> tripDTOSList = tripService.findTripByType(type);
        return tripDTOSList;
    }

    @GetMapping(path = "findTripByDepartureFlight/{departureFlight}")
    public List<TripDTO> findTripByDepartureFlight(@PathVariable Date departureFlight){
        List<TripDTO> tripDTOSList = tripService.findTripByDepartureFlight(departureFlight);
        return tripDTOSList;
    }
}
