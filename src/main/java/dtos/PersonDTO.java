package dtos;

import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO implements Serializable {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<HobbyInnerDTO> hobbies = new ArrayList<>();
    private final PersonDTO.AddressInnerDTO address;
    private final List<PhoneInnerDTO> phoneNumbers = new ArrayList<>();

    public PersonDTO(Integer id, String firstName, String lastName, String email, AddressInnerDTO address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        person.getHobbies().forEach( hobby -> {
            hobbies.add(new HobbyInnerDTO(hobby));
        });
        this.address = new AddressInnerDTO(person.getAddress());
        person.getPhones().forEach( phone -> {
            phoneNumbers.add(new PhoneInnerDTO(phone));
        });
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

    public AddressInnerDTO getAddress() {
        return address;
    }

    public List<PhoneInnerDTO> getPhoneNumbers() {
        return phoneNumbers;
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
    public static class AddressInnerDTO implements Serializable {
        private final Integer id;
        private final String street;
        private final String additionalInfo;

        public AddressInnerDTO(Integer id, String street, String additionalInfo) {
            this.id = id;
            this.street = street;
            this.additionalInfo = additionalInfo;
        }

        public AddressInnerDTO(Address address) {
            this.id = address.getId();
            this.street = address.getStreet();
            this.additionalInfo = address.getAdditionalInfo();
        }


        public Integer getId() {
            return id;
        }

        public String getStreet() {
            return street;
        }

        public String getAdditionalInfo() {
            return additionalInfo;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "street = " + street + ", " +
                    "additionalInfo = " + additionalInfo + ")";
        }
    }
    public static class PhoneInnerDTO implements Serializable {
        private final Integer id;
        private final String number;
        private final String description;

        public PhoneInnerDTO(Integer id, String number, String description) {
            this.id = id;
            this.number = number;
            this.description = description;
        }

        public PhoneInnerDTO(Phone phone) {
            this.id = phone.getId();
            this.number = phone.getNumber();
            this.description = phone.getDescription();
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

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "number = " + number + ", " +
                    "description = " + description + ")";
        }
    }
}
