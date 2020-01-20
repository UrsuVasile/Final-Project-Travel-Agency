package sda.com.travel.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.com.travel.bussiness.service.CityService;
import sda.com.travel.frontend.dto.CityDTO;

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
}
