package dtos;

import java.io.Serializable;
import java.util.Set;

public class HobbyDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String description;
    private final Set<PersonInnerDTO> persons;

    public HobbyDto(Integer id, String name, String description, Set<PersonInnerDTO> persons) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.persons = persons;
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

    public Set<PersonInnerDTO> getPersons() {
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
