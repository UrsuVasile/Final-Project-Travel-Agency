package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.ContinentDTO;
import sda.com.travel.frontend.dto.RoomDTO;
import sda.com.travel.persistence.dao.*;
import sda.com.travel.persistence.entity.*;

@Service
public class RoomService {

    @Autowired
    RoomDAO roomDAO;
    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

//    public void insertRoom(RoomDTO roomDTO){
//
//        Room room = new Room();
//        room.setFromDate(roomDTO.getFromDate());
//        room.setNrOfDoubleRooms(roomDTO.getNrOfDoubleRooms());
//        room.setNrOfExtraBeds(roomDTO.getNrOfExtraBeds());
//        room.setNrOfSingleRooms(roomDTO.getNrOfSingleRooms());
//        room.setPriceForDoubleRoom(roomDTO.getPriceForDoubleRoom());
//        room.setPriceForExtraBed(roomDTO.getPriceForExtraBed());
//        room.setPriceForSingleRoom(roomDTO.getPriceForSingleRoom());
//        room.setToDate(roomDTO.getToDate());
//
//        Continent continent = continentDAO.findContinentByName(roomDTO.getHotelDTO().getCityDTO().getCountryDTO().getContinentDTO().getName());
//        continent.setName(roomDTO.getHotelDTO().getCityDTO().getCountryDTO().getCountryName());
//
//        Country country = countryDAO.findCountryByName(roomDTO.getHotelDTO().getCityDTO().getCountryDTO().getCountryName());
//        country.setCountryName(roomDTO.getHotelDTO().getCityDTO().getCountryDTO().getCountryName());
//        country.setContinent(continent);
//
//        City city = cityDAO.findCityByName(roomDTO.getHotelDTO().getCityDTO().getName());
//        city.setName(roomDTO.getHotelDTO().getCityDTO().getName());
//        city.setCountry(country);
//
//        Hotel hotel = hotelDAO.findHotelByNameWithSingleResult(roomDTO.getHotelDTO().getName());
//        hotel.setName(roomDTO.getHotelDTO().getName());
//        hotel.setDescription(roomDTO.getHotelDTO().getDescription());
//        hotel.setStandard(roomDTO.getHotelDTO().getStandard());
//        hotel.setCity(city);
//
//       // room.setHotel(hotel);
//
//        roomDAO.insertRoom(room);
//    }
}
