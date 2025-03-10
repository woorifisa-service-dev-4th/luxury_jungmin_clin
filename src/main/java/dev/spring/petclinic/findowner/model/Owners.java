package dev.spring.petclinic.findowner.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Owners {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private List<Pet> pets;
}
