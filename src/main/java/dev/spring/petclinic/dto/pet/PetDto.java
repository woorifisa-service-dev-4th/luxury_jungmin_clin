package dev.spring.petclinic.dto.pet;

import dev.spring.petclinic.model.pet.Pet;
import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PetDto {
    private final int id;
    private final int ownerId;
    private final String name;
    private final LocalDate birthDate;
    private final int typeId;
    private final List<VisitDto> visits;

    @ConstructorProperties({ "id", "ownerId", "name", "birthDate", "typeId", "visits" })
    public PetDto(int id, int ownerId, String name, LocalDate birthDate, int typeId, List<VisitDto> visits) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.birthDate = birthDate;
        this.typeId = typeId;
        this.visits = visits;
    }

    @ConstructorProperties({ "id", "ownerId", "name", "birthDate", "typeId" })
    public PetDto(int id, int ownerId, String name, LocalDate birthDate, int typeId) {
        this(id, ownerId, name, birthDate, typeId, Collections.emptyList());
    }

    public Pet toEntity() {
        return Pet.builder()
                .id(this.id)
                .ownerId(this.ownerId)
                .name(this.name)
                .birthDate(this.birthDate)
                .typeId(this.typeId)
                .visits(this.visits != null ? this.visits.stream().map(VisitDto::toEntity).collect(Collectors.toList())
                        : null)
                .build();
    }

    public static PetDto fromEntity(Pet pet) {
        return new PetDto(
                pet.getId(),
                pet.getOwnerId(),
                pet.getName(),
                pet.getBirthDate(),
                pet.getTypeId(),
                pet.getVisits() != null
                        ? pet.getVisits().stream().map(VisitDto::fromEntity).collect(Collectors.toList())
                        : null);
    }
}
