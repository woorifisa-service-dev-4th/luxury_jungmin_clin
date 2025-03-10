package dev.spring.petclinic.searchResult.dto;

import dev.spring.petclinic.searchResult.model.Pets;
import dev.spring.petclinic.searchResult.service.SearchResultService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    private int id;
    private int ownerId;
    private String name;
    private LocalDate birthDate;
    private String type;

    private List<PetVisitDto> visits;

    public static PetDto from(Pets pet, List<PetVisitDto> visits) {
        return PetDto.builder()
                .id(pet.getId())
                .ownerId(pet.getOwnerId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .type(pet.getType())
                .visits(visits)
                .build();
    }

    public static List<PetDto> from(List<Pets> pets, SearchResultService service) {
        return pets.stream()
                .map(pet -> from(pet, service.findPetVisitsByPet(pet.getId())))
                .collect(Collectors.toList());
    }
}
