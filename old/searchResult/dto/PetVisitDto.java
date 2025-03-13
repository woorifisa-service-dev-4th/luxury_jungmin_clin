package dev.spring.petclinic.old.searchResult.dto;

import dev.spring.petclinic.old.searchResult.model.Visits;
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
public class PetVisitDto {
    private int id;
    private int petId;
    private LocalDate visitDate;
    private String description;

    public static PetVisitDto from(Visits visit) {
        return PetVisitDto.builder()
                .id(visit.getId())
                .petId(visit.getPetId())
                .visitDate(visit.getVisitDate())
                .description(visit.getDescription())
                .build();
    }

    public static List<PetVisitDto> from(List<Visits> visits) {
        return visits.stream()
                .map(PetVisitDto::from)
                .collect(Collectors.toList());
    }
}
