package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.City;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;

@Repository
public class CityDAO {

    HibernateUtil hibernateUtil = new HibernateUtil();

    public void insertCity (City city){
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(city);
        hibernateUtil.closeSessionAndTransaction();
    }

    public City findCityByName(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_city_by_name");
        query.setParameter("name", name);
        City city = (City) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return city;
    }
}
