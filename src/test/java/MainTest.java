import facades.PersonFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainTest
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        //PersonFacade personFacade = PersonFacade.getInstance(emf);


        //System.out.println(personFacade.getPerson("asd"));


    }
}