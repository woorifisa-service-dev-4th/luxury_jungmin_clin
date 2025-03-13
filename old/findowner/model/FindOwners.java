package dev.spring.petclinic.old.findowner.model;

import lombok.Getter;

import java.util.List;

@Getter
public class FindOwners {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private List<Pet> pets;

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
