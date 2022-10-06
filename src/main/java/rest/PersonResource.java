package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/persons")
public class PersonResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade facade =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String getAllPeople() {
        List<PersonDTO> personDTOList = facade.getAllPeople();
        return GSON.toJson(personDTOList);
    }

    @Path("{id}")
    @GET
    @Produces("text/plain")
    public String getPersonById(@PathParam("id") int id) {
        return GSON.toJson(facade.getPersonById(id));
    }

    @Path("hobby/{hobby}")
    @GET
    @Produces("text/plain")
    public String getPersonsByHobby(@PathParam("hobby") String hobby) {
        return GSON.toJson(facade.getPersonsByHobby(hobby));
    }

    @Path("city/{city}")
    @GET
    @Produces("text/plain")
    public String getPersonsByCity(@PathParam("city") String city) {
        return GSON.toJson(facade.getPersonsByCity(city));
    }

    @Path("phone/{number}")
    @GET
    @Produces("text/plain")
    public String getPersonByPhoneNumber(@PathParam("number") String number) {
        return GSON.toJson(facade.getPersonByPhoneNumber(number));
    }

    @Path("city")
    @GET
    @Produces("text/plain")
    public String getAllPersonsCityInfo() {
        List<PersonDTO> personDTOList = facade.getAllPersonsCityInfo();
        return GSON.toJson(personDTOList);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPerson(String jsonInput){
        PersonDTO personDTO = GSON.fromJson(jsonInput, PersonDTO.class);
        PersonDTO returned = facade.createPerson(personDTO);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }

    @PUT
    @Path("update/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updatePerson(@PathParam("id") Integer id, String jsonInput){
        PersonDTO personDTO = GSON.fromJson(jsonInput, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO returned = facade.updatePerson(personDTO);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }

}