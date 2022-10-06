/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.*;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade facade = PersonFacade.getPersonFacade(emf);
        CityInfo cityInfo = new CityInfo("2830","Virum");
        Address address = new Address("Svalehøjvej","18",cityInfo);
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone("82828282","Mobil"));
        phones.add(new Phone("23242526","Hjemme tlf."));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
