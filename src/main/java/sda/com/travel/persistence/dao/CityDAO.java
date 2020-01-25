package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.City;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CityDAO {

    HibernateUtil hibernateUtil = new HibernateUtil();

    public void insertCity (City city){
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(city);
        hibernateUtil.closeSessionAndTransaction();
    }

    public City findCityByName(String name){
        System.out.println(name);
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_city_by_name");
        query.setParameter("name", name);
        City city = (City) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return city;
    }

    public void deleteCity(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("delete_city");
        query.setParameter("name", name);
        query.executeUpdate();
        hibernateUtil.closeSessionAndTransaction();
    }

    public List<City> findCityByNameAndContinentName(City city){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_city_by_name_and_continent_name");
        query.setParameter("cityName", city.getName());
        query.setParameter("countryName", city.getCountry().getCountryName());
        List<City> citiesList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return citiesList;
    }

    public void updateCity(City city) throws Exception{
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("count_citys");
        query.setParameter("name", city.getName());
        Long countCity = (Long) query.getSingleResult();

        if(countCity == 1){
            Query query1 = hibernateUtil.session.createNamedQuery("find_city_by_name");
            query1.setParameter("name", city.getName());

            City city1 = (City) query1.getSingleResult();
            city1.setName(city.getName());
            city1.setCountry(city.getCountry());

            hibernateUtil.session.persist(city1);
        }else throw new Exception();
        hibernateUtil.closeSessionAndTransaction();
    }
}
