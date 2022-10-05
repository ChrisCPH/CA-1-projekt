package facades;

import dtos.AddressDTO;
import dtos.CityInfoDTO;
import entities.Address;
import entities.CityInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityInfoFacade {

    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CityInfoFacade() {
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CityInfoFacade getCityInfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }

    public List<CityInfoDTO> getAllCityInfo() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
            List<CityInfo> cityinfos = query.getResultList();
            return CityInfoDTO.getDTOs(cityinfos);
        } finally {
            em.close();
        }
    }
}
