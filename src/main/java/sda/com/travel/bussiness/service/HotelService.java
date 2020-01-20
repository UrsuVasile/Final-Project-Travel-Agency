package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.CityDTO;
import sda.com.travel.frontend.dto.ContinentDTO;
import sda.com.travel.frontend.dto.CountryDTO;
import sda.com.travel.frontend.dto.HotelDTO;
import sda.com.travel.persistence.dao.CityDAO;
import sda.com.travel.persistence.dao.ContinentDAO;
import sda.com.travel.persistence.dao.CountryDAO;
import sda.com.travel.persistence.dao.HotelDAO;
import sda.com.travel.persistence.entity.City;
import sda.com.travel.persistence.entity.Continent;
import sda.com.travel.persistence.entity.Country;
import sda.com.travel.persistence.entity.Hotel;

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

    public void insertHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setStandard(hotelDTO.getStandard());
        hotel.setCity(cityDAO.findCityByName(hotelDTO.getCityDTO().getName()));

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
}
