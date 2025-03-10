package dev.spring.petclinic.dto.pet;

import dev.spring.petclinic.model.pet.Visit;
import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Getter
@Builder
public class VisitDto {
    private final int id;
    private final int petId;
    private final LocalDate visitDate;
    private final String description;

    @ConstructorProperties({ "id", "petId", "visitDate", "description" })
    public VisitDto(int id, int petId, LocalDate visitDate, String description) {
        this.id = id;
        this.petId = petId;
        this.visitDate = visitDate;
        this.description = description;
    }

    public Visit toEntity() {
        return Visit.builder()
                .id(this.id)
                .petId(this.petId)
                .visitDate(this.visitDate)
                .description(this.description)
                .build();
    }

    public static VisitDto fromEntity(Visit visit) {
        return new VisitDto(
                visit.getId(),
                visit.getPetId(),
                visit.getVisitDate(),
                visit.getDescription());
    }
}
