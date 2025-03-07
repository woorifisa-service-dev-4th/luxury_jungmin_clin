package dev.spring.petclinic.searchResult.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class PetDto {
    private int petId;
    private int ownerId;
    private String name;
    private LocalDate birthDate;
    private String type;

    private List<PetVisitDto> visits;
}
