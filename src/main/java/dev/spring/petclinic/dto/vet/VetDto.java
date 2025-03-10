package dev.spring.petclinic.dto.vet;

import dev.spring.petclinic.model.vet.Vet;
import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.util.List;

@Getter
@Builder
public class VetDto {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final List<String> specialties;

    @ConstructorProperties({ "id", "firstName", "lastName", "specialties" })
    public VetDto(int id, String firstName, String lastName, List<String> specialties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
    }

    public static VetDto fromEntity(Vet vet) {
        return new VetDto(
                vet.getId(),
                vet.getFirstName(),
                vet.getLastName(),
                vet.getSpecialties());
    }
}
