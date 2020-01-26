package sda.com.travel.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.com.travel.bussiness.service.RoomService;
import sda.com.travel.frontend.dto.RoomDTO;


@RestController
@RequestMapping("/room/")
public class RoomController {
//    @Autowired
//    RoomService roomService;
//
//    @PostMapping(path = "insertRoom", consumes = "application/json")
//    public ResponseEntity insertRoom(@RequestBody RoomDTO roomDTO){
//        System.out.println(roomDTO.getFromDate());
//        //roomService.insertRoom(roomDTO);
//        return ResponseEntity.ok("Succes! The Room was inserted in DB!");
//    }
}
