package dev.spring.petclinic.model.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Visit {
    private int id;
    private int petId;
    private LocalDate visitDate;
    private String description;
}
