package dev.spring.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vet {
    private Integer id;
    private String firstName;
    private String lastName;
    private Set<Integer> specialtyIds; // VetSpecialty 테이블을 통해 Specialty와 연결
}
