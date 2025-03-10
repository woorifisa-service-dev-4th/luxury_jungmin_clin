package dev.spring.petclinic.dto.owner;

import dev.spring.petclinic.dto.pet.PetDto;
import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
public class OwnerWithPetsDto {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String telephone;
    private final List<PetDto> pets;

    // 7-argument 생성자 (pets 데이터가 있는 경우)
    @ConstructorProperties({ "id", "firstName", "lastName", "address", "city", "telephone", "pets" })
    public OwnerWithPetsDto(Integer id, String firstName, String lastName, String address, String city,
            String telephone, List<PetDto> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets != null ? pets : Collections.emptyList();
    }

    // 6-argument 생성자 (pets 데이터가 없는 경우 → 빈 리스트)
    @ConstructorProperties({ "id", "firstName", "lastName", "address", "city", "telephone" })
    public OwnerWithPetsDto(Integer id, String firstName, String lastName, String address, String city,
            String telephone) {
        this(id, firstName, lastName, address, city, telephone, Collections.emptyList());
    }
}
