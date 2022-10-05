package facades;

import dtos.AddressDTO;
import entities.Address;
import rest.AddressResource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressFacade {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private AddressFacade() {}

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static AddressFacade getAddressFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    public List<AddressDTO> getAllAddresses() {
        EntityManager em = getEntityManager();
        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a", Address.class);
        List<Address> addresses = query.getResultList();
        return AddressDTO.getDTOs(addresses);
    }

}
