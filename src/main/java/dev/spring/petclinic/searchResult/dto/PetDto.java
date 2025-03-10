package dev.spring.petclinic.searchResult.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    private int id;
    private int ownerId;
    private String name;
    private LocalDate birthDate;
    private int typeId;

    private List<PetVisitDto> visits;

    public void assignOwner(int ownerId) {
        this.ownerId = ownerId;
    }
}
