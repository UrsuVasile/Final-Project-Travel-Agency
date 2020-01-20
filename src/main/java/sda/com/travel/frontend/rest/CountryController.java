package sda.com.travel.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.com.travel.bussiness.service.CountryService;
import sda.com.travel.frontend.dto.CountryDTO;

@RestController
@RequestMapping("/country/")
public class CountryController {

    @Autowired
    CountryService countryService;

    @PostMapping(path = "insertCountry", consumes = "application/json")
    public ResponseEntity insertCountry(@RequestBody CountryDTO countryDTO){
        countryService.insertCountry(countryDTO);
        return ResponseEntity.ok("The country was inserted in DB");
    }
}
