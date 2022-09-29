package facades;

import dtos.RenameMeDTO;
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

    public Person createPerson(int personId, String email, String firstName, String Lastname, String address)
    {
        EntityManager em = emf.createEntityManager();
        Person newPerson = new Person(firstName, Lastname, email);
        em.getTransaction().begin();
        em.persist(newPerson);
        em.getTransaction().commit();
        em.close();
        return newPerson;
    }



}