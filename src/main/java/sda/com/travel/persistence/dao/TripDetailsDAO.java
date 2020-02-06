package sda.com.travel.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.*;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TripDetailsDAO {
    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    public void insertTripDetails(TripDetails tripDetails) {
        //hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(tripDetails);
        //HibernateUtil.getInstance().closeSessionAndTransaction();

    }


}
