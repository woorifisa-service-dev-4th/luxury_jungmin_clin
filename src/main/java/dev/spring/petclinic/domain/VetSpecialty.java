package dev.spring.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VetSpecialty {
    private Integer vetId;        // Vet의 id를 참조
    private Integer specialtyId;  // Specialty의 id를 참조
}
