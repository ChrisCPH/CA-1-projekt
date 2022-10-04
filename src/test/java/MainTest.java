import dtos.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import facades.PersonFacade;
import org.glassfish.json.JsonUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MainTest
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        PersonFacade personFacade = PersonFacade.getPersonFacade(emf);


        //System.out.println(personFacade.getPersonByEmail("asd").toString());
        //System.out.println("hej");

/*
        CityInfo cityInfo = new CityInfo("a", "p");
        Address address = new Address("gade", "h", cityInfo);
        personFacade.createPerson(4, "asdasd", "Jonathan", "Braad", address);

 */
        List<PersonDTO> pers = personFacade.getAllPeople();

        System.out.println(pers);
        }


    }
