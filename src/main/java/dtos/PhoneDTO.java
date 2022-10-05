package dtos;

import entities.Hobby;
import entities.Person;
import entities.Phone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneDTO implements Serializable {
    private final Integer id;
    private final String number;
    private final String description;
    private final List<PersonInnerDTO> person = new ArrayList<>();

    public PhoneDTO(Integer id, String number, String description) {
        this.id = id;
        this.number = number;
        this.description = description;

    }

    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

    public static List<PhoneDTO> getDTOs(List<Phone> phones) {
        List<PhoneDTO> phoneDTOList = new ArrayList<>();
        phones.forEach( phone -> phoneDTOList.add(new PhoneDTO(phone)));
        return phoneDTOList;
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public List<PersonInnerDTO> getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "number = " + number + ", " +
                "description = " + description + ", " +
                "person = " + person + ")";
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
        
        public PersonInnerDTO(Person person)
        {
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
