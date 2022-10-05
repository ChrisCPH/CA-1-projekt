package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Person;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;

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

    public List<PersonDTO> getAllPeople()
    {
        EntityManager em = getEntityManager();

        try
        {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> person = query.getResultList();
            return PersonDTO.getDTOs(person);
        }
        finally
        {
            em.close();
        }
    }

    public PersonDTO getPersonByEmail(String email) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email", Person.class);
        query.setParameter("email", email);
        Person rms = query.getSingleResult();
        return new PersonDTO(rms);
    }

    public PersonDTO createPerson(String email, String firstName, String Lastname, Address addressId)
    {
        EntityManager em = getEntityManager();
        Person newPerson = new Person(firstName, Lastname, email, addressId);
        em.getTransaction().begin();
        em.persist(addressId);
        em.persist(addressId.getCityInfo());
        em.persist(newPerson);
        em.getTransaction().commit();
        em.close();
        return new PersonDTO(newPerson);
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

    public PersonDTO deletePerson (Integer id) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        try {

            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    public List<Person> getPersonsByHobby(String hobbyId) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies hob JOIN hob.persons h WHERE h.id = :id", Person.class);
        query.setParameter("id", hobbyId);
        List<Person> rms = query.getResultList();
        return rms;
    }

    public List<Person> getPersonsByCityInfo(String zipcode) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("", Person.class);
        query.setParameter("zipcode", zipcode);
        List<Person> rms = query.getResultList();
        return rms;
    }
}