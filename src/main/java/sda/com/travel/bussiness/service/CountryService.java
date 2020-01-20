package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.CountryDTO;
import sda.com.travel.persistence.dao.ContinentDAO;
import sda.com.travel.persistence.dao.CountryDAO;
import sda.com.travel.persistence.entity.Country;

@Service
public class CountryService {

    @Autowired
    CountryDAO countryDAO;

    @Autowired
    ContinentDAO continentDAO;

    public void insertCountry(CountryDTO countryDTO) {

        Country country = new Country();
        country.setCountryName(countryDTO.getCountryName());
        country.setContinent(continentDAO.findContinentByName(countryDTO.getContinentDTO().getName()));

        countryDAO.insertCountry(country);
    }
}
