package dtos;

import entities.Address;
import entities.Hobby;
import entities.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO implements Serializable {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<HobbyInnerDTO> hobbies = new ArrayList<>();
    private final String address;
    private final String phoneNumber;

    public PersonDTO(Integer id, String firstName, String lastName, String email, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        person.getHobbies().forEach( hobby -> {
            hobbies.add(new HobbyInnerDTO(hobby));
        });
        this.address = person.getAddressString();
        this.phoneNumber = person.getPhonesString();
    }

    public static List<PersonDTO> getDTOs(List<Person> persons) {
        List<PersonDTO> personDTOList = new ArrayList<>();
        persons.forEach( person -> {
            personDTOList.add(new PersonDTO(person));
        });
        return personDTOList;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<HobbyInnerDTO> getHobbies() {
        return hobbies;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "email = " + email + ", " +
                "hobbies = " + hobbies + ")";
    }

    public static class HobbyInnerDTO implements Serializable {
        private final Integer id;
        private final String name;
        private final String description;

        public HobbyInnerDTO(Integer id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public HobbyInnerDTO(Hobby hobby) {
            this.id = hobby.getId();
            this.name = hobby.getName();
            this.description = hobby.getDescription();
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "description = " + description + ")";
        }
    }
}
