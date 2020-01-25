package sda.com.travel.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.com.travel.bussiness.service.HotelService;
import sda.com.travel.frontend.dto.HotelDTO;
import java.util.List;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping(path = "insertHotel", consumes = "application/json")
    public ResponseEntity insertHotel(@RequestBody HotelDTO hotelDTO){
        hotelService.insertHotel(hotelDTO);
        return ResponseEntity.ok("The Hotel was inserted in DB!");
    }

    @DeleteMapping(path = "deleteHotel/{hotelName}")
    public ResponseEntity deleteHotel (@PathVariable String hotelName){
        hotelService.deleteHotel(hotelName);
        return ResponseEntity.ok("The Hotel was deleted!");
    }

    @GetMapping(path = "findHotelByName/{name}")
    public List<HotelDTO> findHotelByName(@PathVariable String name){
        List<HotelDTO> hotelDTOList = hotelService.findHotelByName(name);
        return hotelDTOList;
    }

    @PutMapping(path = "updateHotel", consumes = "application/json")
    public ResponseEntity updateHotel(@RequestBody HotelDTO hotelDTO){
        try {
            hotelService.updateHotel(hotelDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error! The hotel was not updated");
        }
        return ResponseEntity.ok("Succes! The hotel was updated!");
    }

    @GetMapping(path = "findHotelByNameAndCityName", consumes = "application/json")
    public List<HotelDTO> findHotelByNameAndCityName(@RequestBody HotelDTO hotelDTO){
        List<HotelDTO> hotelDTOList = hotelService.findHotelByNameAndCityName(hotelDTO);
        return hotelDTOList;
    }
}
