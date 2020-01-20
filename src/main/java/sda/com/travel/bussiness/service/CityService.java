package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.CityDTO;
import sda.com.travel.persistence.dao.CityDAO;
import sda.com.travel.persistence.dao.CountryDAO;
import sda.com.travel.persistence.entity.City;

@Service
public class CityService {

    @Autowired
    CityDAO cityDAO;

    @Autowired
    CountryDAO countryDAO;

    public void insertCity(CityDTO cityDTO){
        City city = new City();

        city.setName(cityDTO.getName());
        city.setCountry(countryDAO.findCountryByName(cityDTO.getCountryDTO().getCountryName()));

        cityDAO.insertCity(city);
    }
}
