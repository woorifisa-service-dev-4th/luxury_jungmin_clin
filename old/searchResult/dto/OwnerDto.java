package dev.spring.petclinic.old.searchResult.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OwnerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private List<PetDto> pets;
}
