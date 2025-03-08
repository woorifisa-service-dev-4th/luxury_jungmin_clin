package dev.spring.petclinic.searchResult.model;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Pets {
    private int id;
    private int ownerId;
    private String name;
    private LocalDate birthDate;
    private String type;


    private List<Visits> visits;
}
