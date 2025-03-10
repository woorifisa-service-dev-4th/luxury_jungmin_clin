package dev.spring.petclinic.model.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Pet {
    private int id;
    private int ownerId;
    private String name;
    private LocalDate birthDate;
    private int typeId;
    private List<Visit> visits;
}
