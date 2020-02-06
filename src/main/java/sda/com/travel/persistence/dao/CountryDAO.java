package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Country;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;

@Repository
public class CountryDAO {

    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    public void insertCountry(Country country){
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(country);
        hibernateUtil.closeSessionAndTransaction();
    }

    public Country findCountryByName(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_country_by_name");
        query.setParameter("countryName", name);
        Country country = (Country) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return country;
    }
}
