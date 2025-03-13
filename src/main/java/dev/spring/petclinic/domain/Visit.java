package dev.spring.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    private Integer id;
    private Integer petId;       // Pet의 id를 참조
    private LocalDate visitDate;
    private String description;
}