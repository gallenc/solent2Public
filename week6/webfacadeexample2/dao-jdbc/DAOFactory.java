package org.solent.com600.example.journeyplanner.jpadao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.solent.com600.example.journeyplanner.model.RideoutDAO;
import org.solent.com600.example.journeyplanner.model.SysUserDAO;

public class DAOFactory {
    
    // TODO FACTORY MAY WANT TO CLOSE ENTITY MANAGER AT END OF SESSION

    private static final String PERSISTENCE_UNIT_NAME = "motorcyclePersistence";
    private static EntityManagerFactory factory;
    private static EntityManager em;
    private static SysUserDAO sysUserDAO;
    private static RideoutDAO rideoutDAO;
    
    static {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        sysUserDAO = new SysUserDAOJpaImpl(em);
        rideoutDAO = new RideoutDAOJpaImpl(em);
    }

    public static RideoutDAO getRideoutDAO() {
        return rideoutDAO;
    }

    public static SysUserDAO getSysUserDAO() {
        return sysUserDAO;
    }

}
