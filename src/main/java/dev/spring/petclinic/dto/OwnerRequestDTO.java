package dev.spring.petclinic.dto;

public record OwnerRequestDTO(
        String firstName,
        String lastName,
        String address,
        String city,
        String telephone
) {}