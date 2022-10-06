package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import dtos.CityInfoDTO;
import facades.AddressFacade;
import facades.CityInfoFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cities")
public class CityInfoResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CityInfoFacade cityinfofacade = CityInfoFacade.getCityInfoFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String getAllCityInfo() {
        List<CityInfoDTO> cityinfoDTOList = cityinfofacade.getAllCityInfo();
        return GSON.toJson(cityinfoDTOList);
    }
}