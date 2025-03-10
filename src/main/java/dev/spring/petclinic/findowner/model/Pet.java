package dev.spring.petclinic.findowner.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Pet {
    private int id;
    private int ownerId;
    private String name;
}
