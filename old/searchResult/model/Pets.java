package dev.spring.petclinic.old.searchResult.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pets {
    private int id;
    private int ownerId;
    private String name;
    private LocalDate birthDate;
    private int typeId;

    private List<Visits> visits;
}
