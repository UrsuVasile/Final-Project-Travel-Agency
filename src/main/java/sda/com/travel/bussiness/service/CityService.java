package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.CityDTO;
import sda.com.travel.frontend.dto.ContinentDTO;
import sda.com.travel.frontend.dto.CountryDTO;
import sda.com.travel.persistence.dao.CityDAO;
import sda.com.travel.persistence.dao.ContinentDAO;
import sda.com.travel.persistence.dao.CountryDAO;
import sda.com.travel.persistence.entity.City;
import sda.com.travel.persistence.entity.Continent;
import sda.com.travel.persistence.entity.Country;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

    public void insertCity(CityDTO cityDTO) {
        City city = new City();

        city.setName(cityDTO.getName());
        city.setCountry(countryDAO.findCountryByName(cityDTO.getCountryDTO().getCountryName()));

        cityDAO.insertCity(city);
    }

    public void deleteCity(String name) {
        cityDAO.deleteCity(name);
    }

    public CityDTO findCityByName(String name) {
        City city = cityDAO.findCityByName(name);
        CityDTO cityDTO = new CityDTO();

        cityDTO.setName(city.getName());

        Country country = countryDAO.findCountryByName(city.getCountry().getCountryName());
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryName(country.getCountryName());
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setName(country.getContinent().getName());
        countryDTO.setContinentDTO(continentDTO);
        cityDTO.setCountryDTO(countryDTO);

        return cityDTO;
    }

    public List<CityDTO> findCityByNameAndContinentName(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());

        Country country = countryDAO.findCountryByName(cityDTO.getCountryDTO().getCountryName());
        Continent continent = continentDAO.findContinentByName(country.getContinent().getName());

        city.setCountry(country);

        List<City> citiesList = cityDAO.findCityByNameAndContinentName(city);

        List<CityDTO> cityDTOSList = new ArrayList<>();

        for (City c : citiesList) {
            CityDTO cityDTO1 = new CityDTO();
            cityDTO1.setName(c.getName());

            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(c.getCountry().getContinent().getName());

            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountryName(c.getCountry().getCountryName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTO1.setCountryDTO(countryDTO);
            cityDTOSList.add(cityDTO1);
        }
        return cityDTOSList;
    }

    public void updateCity(CityDTO cityDTO) throws Exception {
        City city = new City();
        city.setName(cityDTO.getName());
        Country country = countryDAO.findCountryByName(cityDTO.getCountryDTO().getCountryName());
        Continent continent = continentDAO.findContinentByName(country.getContinent().getName());

        city.setCountry(country);

        cityDAO.updateCity(city);
    }
}
