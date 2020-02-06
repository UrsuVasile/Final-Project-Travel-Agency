package sda.com.travel.utils.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static HibernateUtil hibernateUtil = null;

    public Transaction transaction;
    public Session session ;
    public SessionFactory sessionFactory;

    private HibernateUtil() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources sources = new MetadataSources(registry);
        Metadata metadata = sources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static HibernateUtil getInstance(){
        if(hibernateUtil == null){
            hibernateUtil = new HibernateUtil();
        }
        return hibernateUtil;
    }

    public void openSessionAndTransaction() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    public void closeSessionAndTransaction() {
        transaction.commit();
        session.close();
    }
}
