package dtos;

import entities.Address;
import entities.CityInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link entities.CityInfo} entity
 */
public class CityInfoDTO implements Serializable {
    private final Integer id;
    private final String zipcode;
    private final String city;
    private final Set<Address> addresses;

    public CityInfoDTO(Integer id, String zipcode, String city, Set<Address> addresses) {
        this.id = id;
        this.zipcode = zipcode;
        this.city = city;
        this.addresses = addresses;
    }

    public CityInfoDTO(CityInfo cityInfo) {
        this.id = cityInfo.getId();
        this.zipcode = cityInfo.getZipcode();
        this.city = cityInfo.getCity();
        this.addresses = cityInfo.getAddresses();
    }

    public static List<CityInfoDTO> getDTOs(List<CityInfo> cityinfos) {
        List<CityInfoDTO> cityDtoList = new ArrayList<>();
        cityinfos.forEach( cityInfo -> {
            cityDtoList.add(new CityInfoDTO(cityInfo));
        });
        return cityDtoList;
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

    public Set<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "zipcode = " + zipcode + ", " +
                "city = " + city + ", " +
                "addresses = " + addresses + ")";
    }

    /**
     * A DTO for the {@link entities.Address} entity
     */
    public static class AddressInnerDto implements Serializable {
        private final Integer id;
        private final String street;
        private final String additionalInfo;

        public AddressInnerDto(Integer id, String street, String additionalInfo) {
            this.id = id;
            this.street = street;
            this.additionalInfo = additionalInfo;
        }

        public AddressInnerDto(Address address) {
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
}