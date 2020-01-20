package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Continent;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;

@Repository
public class ContinentDAO {

    HibernateUtil hibernateUtil = new HibernateUtil();

    public void insertContinent(Continent continent){
        hibernateUtil.openSessionAndTransaction();

        hibernateUtil.session.persist(continent);

        hibernateUtil.closeSessionAndTransaction();
    }

    public Continent findContinentByName(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_continent");
        query.setParameter("name", name);
        Continent continent = (Continent) query.getResultList().get(0);
        hibernateUtil.closeSessionAndTransaction();
        return continent;
    }

}
