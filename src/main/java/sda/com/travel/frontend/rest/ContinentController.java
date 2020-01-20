package sda.com.travel.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.com.travel.bussiness.service.ContinentService;
import sda.com.travel.frontend.dto.ContinentDTO;

@RestController
@RequestMapping("/continent/")
public class ContinentController {

    @Autowired
    ContinentService continentService;

    @PostMapping(path = "insertContinent", consumes = "application/json")
    public ResponseEntity insertContinent(@RequestBody ContinentDTO continentDTO){
        continentService.insertContinent(continentDTO);
        return ResponseEntity.ok("The Continent was inserted in DB");
    }
}
