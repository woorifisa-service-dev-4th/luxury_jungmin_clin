package dev.spring.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private Integer typeId;   // PetType의 id를 참조
    private Integer ownerId;  // Owner의 id를 참조
}