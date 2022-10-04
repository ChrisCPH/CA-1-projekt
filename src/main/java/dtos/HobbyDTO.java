package dtos;

import entities.Hobby;
import entities.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HobbyDTO implements Serializable {
    private final Integer id;
    private final String name;
    private final String description;
    private final List<PersonInnerDTO> persons = new ArrayList<>();

    public HobbyDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
        hobby.getPersons().forEach( person -> {
            persons.add(new PersonInnerDTO(person));
        });
    }

    public static List<HobbyDTO> getDTOs(List<Hobby> hobbies) {
        List<HobbyDTO> hobbyDTOList = new ArrayList<>();
        hobbies.forEach( hobby -> {
            hobbyDTOList.add(new HobbyDTO(hobby));
        });
        return hobbyDTOList;
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

    public List<PersonInnerDTO> getPersons() {
        return persons;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "persons = " + persons + ")";
    }

    public static class PersonInnerDTO implements Serializable {
        private final Integer id;
        private final String firstName;
        private final String lastName;
        private final String email;

        public PersonInnerDTO(Integer id, String firstName, String lastName, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public PersonInnerDTO(Person person) {
            this.id = person.getId();
            this.firstName = person.getFirstName();
            this.lastName = person.getLastName();
            this.email = person.getEmail();
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

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "firstName = " + firstName + ", " +
                    "lastName = " + lastName + ", " +
                    "email = " + email + ")";
        }
    }
}
