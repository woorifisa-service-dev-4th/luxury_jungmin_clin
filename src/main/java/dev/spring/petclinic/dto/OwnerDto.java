package dev.spring.petclinic.dto;

import java.util.List;

public record OwnerDto(
        Integer id,
        String firstName,
        String lastName,
        String address,
        String city,
        String telephone,
        List<PetDto> pets
) {
    public OwnerDto(Integer id, String firstName, String lastName, String address, String city, String telephone) {
        this(id, firstName, lastName, address, city, telephone, List.of());
    }
}
