package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import entities.Address;
import facades.AddressFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/address")
public class AddressResource {


private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
private static final AddressFacade addressfacade = AddressFacade.getAddressFacade(EMF);
private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAddresses() {

        List<AddressDTO> addressDTOList = addressfacade.getAllAddresses();
        return GSON.toJson(addressDTOList);
    }

    @DELETE
    @Path("delete/{zipcode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("zipcode") String zipcode) {
        AddressDTO addressDTO = addressfacade.deleteAddress(zipcode);
        return Response.ok().entity(GSON.toJson(addressDTO)).build();
    }
}