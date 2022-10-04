package dtos;

import entities.Address;
import entities.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link entities.Address} entity
 */
public class AddressDTO implements Serializable {
    private final Integer id;
    private final String street;
    private final String additionalInfo;
    private final CityInfoInnerDto cityInfo = new CityInfoInnerDto();
    private final List<PersonInnerDto> people = new ArrayList<>();

    public AddressDTO(Integer id, String street, String additionalInfo) {
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        address.getPeople().forEach(person -> {
            people.add(new PersonInnerDto(person));
        });
    }

    public static List<AddressDTO> getDTOs(List<Address> addresses) {
        List<AddressDTO> addressDTOList = new ArrayList<>();
        addresses.forEach( address -> {
            addressDTOList.add(new AddressDTO(address));
        });
        return addressDTOList;
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

    public CityInfoInnerDto getCityInfo() {
        return cityInfo;
    }

    public List<PersonInnerDto> getPeople() {
        return people;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "street = " + street + ", " +
                "additionalInfo = " + additionalInfo + ", " +
                "cityInfo = " + cityInfo + ", " +
                "people = " + people + ")";
    }

    /**
     * A DTO for the {@link entities.CityInfo} entity
     */
    public static class CityInfoInnerDto implements Serializable {
        private Integer id;
        private String zipcode;
        private String city;

        public CityInfoInnerDto(Integer id, String zipcode, String city) {
            this.id = id;
            this.zipcode = zipcode;
            this.city = city;
        }

        public CityInfoInnerDto(CityInfoInnerDto cityinfo) {
            this.id = cityinfo.getId();
            this.zipcode = cityinfo.getZipcode();
            this.city = cityinfo.getCity();
        }

        public CityInfoInnerDto() {
        }

        public Integer getId() {
            return id;
        }

        public String getZipcode() {
            return zipcode;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "zipcode = " + zipcode + ", " +
                    "city = " + city + ")";
        }
    }

    /**
     * A DTO for the {@link entities.Person} entity
     */
    public static class PersonInnerDto implements Serializable {
        private final Integer id;
        private final String firstName;
        private final String lastName;
        private final String email;

        public PersonInnerDto(Integer id, String firstName, String lastName, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public PersonInnerDto(Person person) {
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