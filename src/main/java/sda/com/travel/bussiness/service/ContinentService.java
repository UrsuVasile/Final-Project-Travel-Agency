package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.ContinentDTO;
import sda.com.travel.persistence.dao.ContinentDAO;
import sda.com.travel.persistence.entity.Continent;

@Service
public class ContinentService {

    @Autowired
    ContinentDAO continentDAO;

    public void insertContinent (ContinentDTO continentDTO){
        Continent continent = new Continent();

        continent.setName(continentDTO.getName());

        continentDAO.insertContinent(continent);
    }
}
