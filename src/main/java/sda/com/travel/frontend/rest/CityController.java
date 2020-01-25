package sda.com.travel.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.com.travel.bussiness.service.CityService;
import sda.com.travel.frontend.dto.CityDTO;

import java.util.List;

@RestController
@RequestMapping("/city/")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping(path = "insertCity", consumes = "application/json")
    public ResponseEntity insertCity(@RequestBody CityDTO cityDTO){
        cityService.insertCity(cityDTO);
        return ResponseEntity.ok("The city was inserted in DB");
    }

    @DeleteMapping(path = "deleteCity/{name}")
    public ResponseEntity deleteCity(@PathVariable String name){
        cityService.deleteCity(name);
        return ResponseEntity.ok("Succes!The City was deleted!");
    }

    @GetMapping(path = "findCityByName/{name}")
    public CityDTO findCityByName(@PathVariable String name){
        return cityService.findCityByName(name);
    }

    @GetMapping(path = "findCityByNameAndContinentName", consumes = "application/json")
    public List<CityDTO> findCityByNameAndContinentName(@RequestBody CityDTO cityDTO){
        return cityService.findCityByNameAndContinentName(cityDTO);
    }

    @PutMapping(path = "updateCity", consumes = "application/json")
    public ResponseEntity updateCity(@RequestBody CityDTO cityDTO){
        try {
            cityService.updateCity(cityDTO);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().body("Error!The City was not updated!");
        }
        return ResponseEntity.ok("The City was updated!");
    }
}
