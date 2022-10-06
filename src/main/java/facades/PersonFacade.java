package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
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

    public PersonDTO getPersonById(Integer id) {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null)
            throw new EntityNotFoundException("Could not find person with id:" + id);
        return new PersonDTO(person);
    }

    public List<PersonDTO> getPersonsByHobby(String hobbyName) {
        List<PersonDTO> personDTOList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :name", Person.class);
        query.setParameter("name", hobbyName);
        query.getResultList().forEach(person -> {
            personDTOList.add(new PersonDTO(person));
        });
        return personDTOList;
    }

    public List<PersonDTO> getPersonsByCity(String city) {
        List<PersonDTO> personDTOList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a JOIN a.cityInfo c WHERE c.city = :city", Person.class);
        query.setParameter("city", city);
        query.getResultList().forEach(person -> {
            personDTOList.add(new PersonDTO(person));
        });
        return personDTOList;
    }

    public PersonDTO getPersonByPhoneNumber(String phoneNumber) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phones ph WHERE ph.number  = :number", Person.class);
        query.setParameter("number", phoneNumber);
        Person person = query.getSingleResult();
        return new PersonDTO(person);
    }

    public List<PersonDTO> getAllPersonsCityInfo() {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a JOIN a.cityInfo c", Person.class);
        List<Person> person = query.getResultList();
        return PersonDTO.getDTOs(person);
    }

    public PersonDTO createPerson(PersonDTO personDTO)
    {
        EntityManager em = getEntityManager();

        Set<Hobby> hobbyList = new LinkedHashSet<>();
        personDTO.getHobbies().forEach(hobbyInnerDTO -> {
            hobbyList.add(em.find(Hobby.class, hobbyInnerDTO.getId()));
        });

        Set<Phone> phoneList = new LinkedHashSet<>();
        personDTO.getPhoneNumbers().forEach(phoneInnerDTO -> {
            phoneList.add(em.find(Phone.class, phoneInnerDTO.getId()));
        });
        Address address = em.find(Address.class, personDTO.getAddress().getId());
        Person newPerson = new Person(personDTO.getId(), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getEmail(), address);
        newPerson.setHobbies(hobbyList);
        newPerson.setPhones(phoneList);
        em.getTransaction().begin();
        em.persist(newPerson);
        em.getTransaction().commit();
        em.close();
        return new PersonDTO(newPerson);
    }

    public PersonDTO updatePerson(PersonDTO personDTO)
    {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, personDTO.getId());
        if(person == null) {
            throw new EntityNotFoundException("No such person with id:"+personDTO.getId());
        }
        Set<Hobby> hobbyList = new LinkedHashSet<>();
        personDTO.getHobbies().forEach(hobbyInnerDTO -> {
            hobbyList.add(em.find(Hobby.class, hobbyInnerDTO.getId()));
        });

        Set<Phone> phoneList = new LinkedHashSet<>();
        personDTO.getPhoneNumbers().forEach(phoneInnerDTO -> {
            phoneList.add(em.find(Phone.class, phoneInnerDTO.getId()));
        });
        Address address = em.find(Address.class, personDTO.getAddress().getId());
        Person updatedPerson = new Person(personDTO.getId(), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getEmail(), address);
        updatedPerson.setHobbies(hobbyList);
        updatedPerson.setPhones(phoneList);

        em.getTransaction().begin();
        em.persist(updatedPerson);
        em.getTransaction().commit();
        em.close();
        return new PersonDTO(updatedPerson);
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

    public List<Person> getPersonsByCityInfo(String zipcode) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("", Person.class);
        query.setParameter("zipcode", zipcode);
        List<Person> rms = query.getResultList();
        return rms;
    }
}