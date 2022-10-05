package facades;

import dtos.HobbyDTO;
import dtos.HobbyDTO;
import entities.Address;
import entities.Hobby;
import entities.Hobby;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class HobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    private HobbyFacade() {
    }

    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<HobbyDTO> getAllHobbies()
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
            List<Hobby> hobby = query.getResultList();
            return HobbyDTO.getDTOs(hobby);
        }
        finally
        {
            em.close();
        }
    }

    public HobbyDTO getHobbyByID(Integer id) {
        EntityManager em = getEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.id = :hobby_id", Hobby.class);
        query.setParameter("hobby_id", id);
        Hobby rms = query.getSingleResult();
        return new HobbyDTO(rms);
    }

    public HobbyDTO createHobby(String name, String description)
    {
        EntityManager em = getEntityManager();
        Hobby newHobby = new Hobby(name, description);
        em.getTransaction().begin();
        em.persist(newHobby);
        em.getTransaction().commit();
        em.close();
        return new HobbyDTO(newHobby);
    }

    public void editHobby(Integer id, String name, String description)
    {
        EntityManager em = emf.createEntityManager();
        Hobby hobby = em.find(Hobby.class, id);
        em.getTransaction().begin();
        hobby.setName(name);
        hobby.setDescription(description);
        em.getTransaction().commit();
        em.close();
    }
}

