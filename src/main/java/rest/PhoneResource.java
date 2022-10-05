package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PhoneDTO;
import facades.PhoneFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/phone")
public class PhoneResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PhoneFacade facade =  PhoneFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();



    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findPersonByPhone()
    {
        List<PhoneDTO> phoneDTOList = facade.findPersonByPhone();


        return GSON.toJson(phoneDTOList);
    }
}