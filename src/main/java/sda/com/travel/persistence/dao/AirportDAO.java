package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Airport;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;

@Repository
public class AirportDAO {
    HibernateUtil hibernateUtil = new HibernateUtil();

    public Airport findAirportByName(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_airport_by_name");
        query.setParameter("name", name);
        Airport airport = (Airport) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return airport;
    }
}
