package dev.spring.petclinic.old.createowner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;
import lombok.With;

@Value
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Owner {
    Integer id;
    String firstName;
    String lastName;
    String address;
    String city;
    String telephone;
}
