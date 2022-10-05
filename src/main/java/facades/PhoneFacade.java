package facades;

import dtos.PhoneDTO;
import entities.Phone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PhoneFacade {


    private static PhoneFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PhoneFacade() {}


    public static PhoneFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public List<PhoneDTO> findPersonByPhone()
    {

        EntityManager em = getEntityManager();
        try
        {

            TypedQuery <Phone> query = em.createQuery("SELECT p FROM Phone p", Phone.class);
            List <Phone> phones = query.getResultList();
            return PhoneDTO.getDTOs(phones);


        }finally
        {
            em.close();
        }
    }
}
