package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.*;
import sda.com.travel.persistence.dao.*;
import sda.com.travel.persistence.entity.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    HotelDAO hotelDAO;

    @Autowired
    CityDAO cityDAO;

    @Autowired
    CountryDAO countryDAO;

    @Autowired
    ContinentDAO continentDAO;

    @Autowired
    RoomDAO roomDAO;

    public void insertHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setStandard(hotelDTO.getStandard());
        hotel.setCity(cityDAO.findCityByName(hotelDTO.getCityDTO().getName()));

        Room room = new Room();
        room.setToDate(hotelDTO.getRoomDTO().getToDate());
        room.setPriceForSingleRoom(hotelDTO.getRoomDTO().getPriceForSingleRoom());
        room.setPriceForExtraBed(hotelDTO.getRoomDTO().getPriceForExtraBed());
        room.setPriceForDoubleRoom(hotelDTO.getRoomDTO().getPriceForDoubleRoom());
        room.setNrOfSingleRooms(hotelDTO.getRoomDTO().getNrOfSingleRooms());
        room.setNrOfExtraBeds(hotelDTO.getRoomDTO().getNrOfExtraBeds());
        room.setNrOfDoubleRooms(hotelDTO.getRoomDTO().getNrOfDoubleRooms());
        room.setFromDate(hotelDTO.getRoomDTO().getFromDate());

        room.setHotel(hotel);

        hotel.setRoom(room);

        hotelDAO.insertHotel(hotel);
    }

    public void deleteHotel(String name) {
        hotelDAO.deleteHotel(name);
    }

    public List<HotelDTO> findHotelByName(String name) {
        List<Hotel> hotelsList = hotelDAO.findHotelByName(name);
        List<HotelDTO> hotelDTOList = new ArrayList<>();

        for (Hotel h : hotelsList) {
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(h.getName());
            hotelDTO.setDescription(h.getDescription());
            hotelDTO.setStandard(h.getStandard());

            Room room = roomDAO.findRoomsByHotelName(h.getName());
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setFromDate(room.getFromDate());
            roomDTO.setToDate(room.getToDate());
            roomDTO.setNrOfDoubleRooms(room.getNrOfDoubleRooms());
            roomDTO.setNrOfExtraBeds(room.getNrOfExtraBeds());
            roomDTO.setNrOfSingleRooms(room.getNrOfSingleRooms());
            roomDTO.setPriceForDoubleRoom(room.getPriceForDoubleRoom());
            roomDTO.setPriceForExtraBed(room.getPriceForExtraBed());
            roomDTO.setPriceForSingleRoom(room.getPriceForSingleRoom());
            hotelDTO.setRoomDTO(roomDTO);


            Continent continent = continentDAO.findContinentByName(h.getCity().getCountry().getContinent().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(h.getCity().getCountry().getContinent().getName());

            Country country = countryDAO.findCountryByName(h.getCity().getCountry().getCountryName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountryName(country.getCountryName());
            countryDTO.setContinentDTO(continentDTO);

            City city = cityDAO.findCityByName(h.getCity().getName());
            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(city.getName());
            cityDTO.setCountryDTO(countryDTO);

            hotelDTO.setCityDTO(cityDTO);

            hotelDTOList.add(hotelDTO);
        }
        return hotelDTOList;
    }

    public void updateHotel(HotelDTO hotelDTO) throws Exception {
        Hotel hotel = new Hotel();
        hotel.setStandard(hotelDTO.getStandard());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setName(hotelDTO.getName());

        Room room = new Room();
        room.setFromDate(hotelDTO.getRoomDTO().getFromDate());
        room.setNrOfDoubleRooms(hotelDTO.getRoomDTO().getNrOfDoubleRooms());
        room.setNrOfExtraBeds(hotelDTO.getRoomDTO().getNrOfExtraBeds());
        room.setNrOfSingleRooms(hotelDTO.getRoomDTO().getNrOfSingleRooms());
        room.setPriceForDoubleRoom(hotelDTO.getRoomDTO().getPriceForDoubleRoom());
        room.setPriceForExtraBed(hotelDTO.getRoomDTO().getPriceForExtraBed());
        room.setPriceForSingleRoom(hotelDTO.getRoomDTO().getPriceForSingleRoom());
        room.setToDate(hotelDTO.getRoomDTO().getToDate());

        room.setHotel(hotel);

        City city = cityDAO.findCityByName(hotelDTO.getCityDTO().getName());
        Country country = countryDAO.findCountryByName(city.getCountry().getCountryName());
        Continent continent = continentDAO.findContinentByName(country.getContinent().getName());
        hotel.setCity(city);
        hotel.setRoom(room);

        hotelDAO.updateHotel(hotel);
    }

    public List<HotelDTO> findHotelByNameAndCityName(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setStandard(hotelDTO.getStandard());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setName(hotelDTO.getName());
        City city = cityDAO.findCityByName(hotelDTO.getCityDTO().getName());
        Country country = countryDAO.findCountryByName(city.getCountry().getCountryName());
        Continent continent = continentDAO.findContinentByName(country.getContinent().getName());
        hotel.setCity(city);

        List<Hotel> hotelsList = hotelDAO.findHotelByNameAndCityName(hotel);

        List<HotelDTO> hotelDTOList = new ArrayList<>();

        for (Hotel h : hotelsList) {
            HotelDTO hotelDTO1 = new HotelDTO();
            hotelDTO1.setStandard(h.getStandard());
            hotelDTO1.setDescription(h.getDescription());
            hotelDTO1.setName(h.getName());

            Continent continent1 = continentDAO.findContinentByName(h.getCity().getCountry().getContinent().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(h.getCity().getCountry().getContinent().getName());

            Country country1 = countryDAO.findCountryByName(h.getCity().getCountry().getCountryName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountryName(country1.getCountryName());
            countryDTO.setContinentDTO(continentDTO);

            City city1 = cityDAO.findCityByName(h.getCity().getName());
            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(city1.getName());
            cityDTO.setCountryDTO(countryDTO);

            hotelDTO1.setCityDTO(cityDTO);

            hotelDTOList.add(hotelDTO1);
        }
        return hotelDTOList;
    }
}
