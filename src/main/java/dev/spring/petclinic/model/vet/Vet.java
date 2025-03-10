package dev.spring.petclinic.model.vet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Builder
@Getter
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Vet {
    private int id;
    private String firstName;
    private String lastName;
    private List<String> specialties;
}
