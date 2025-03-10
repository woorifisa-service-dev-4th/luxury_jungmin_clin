package dev.spring.petclinic.createowner.entity;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder(toBuilder = true)
@With // 필요시 id 변경 등을 위한 withId() 메서드를 생성
public class Owner {
    Integer id;
    String firstName;
    String lastName;
    String address;
    String city;
    String telephone;
}