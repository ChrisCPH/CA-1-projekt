package facades;

import com.mysql.cj.xdevapi.AddResult;
import dtos.RenameMeDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade() {
    }

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static List<Person> getAllPeople()
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            return query.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public Person getPersonByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email", Person.class);
        query.setParameter("email", email);
        Person rms = query.getSingleResult();
        return rms;
    }

    public Person createPerson(int personId, String email, String firstName, String Lastname, Address addressId)
    {
        EntityManager em = emf.createEntityManager();
        Person newPerson = new Person(personId, firstName, Lastname, email, addressId);
        em.getTransaction().begin();
        em.persist(addressId);
        em.persist(addressId.getCityInfo());
        em.persist(newPerson);
        em.getTransaction().commit();
        em.close();
        return newPerson;
    }

    public List<Person> getPersonsByEmail(String hobbyId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies hob JOIN hob.persons h WHERE h.id = :id", Person.class);
        query.setParameter("id", hobbyId);
        List<Person> rms = query.getResultList();
        return rms;
    }

    public void editPerson(long id, String email, String firstname, String lastname, Address address)
    {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        em.getTransaction().begin();
        person.setEmail(email);
        person.setFirstName(firstname);
        person.setLastName(lastname);
        person.setAddress(address);
        em.getTransaction().commit();
        em.close();
    }




}