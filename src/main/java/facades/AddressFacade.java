package facades;

import dtos.AddressDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.Person;
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

    public AddressDTO deleteAddress (String zipcode) {
        EntityManager em = getEntityManager();
        Address address = em.find(Address.class, zipcode);
        try {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AddressDTO(address);
    }

}
