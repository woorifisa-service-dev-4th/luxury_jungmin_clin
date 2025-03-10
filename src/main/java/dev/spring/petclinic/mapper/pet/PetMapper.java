package dev.spring.petclinic.mapper.pet;

import dev.spring.petclinic.model.pet.Pet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PetMapper {
    // ownerId로 Pet 조회
    List<Pet> selectPetsByOwnerId(@Param("ownerId") int ownerId);

    // Pet 등록
    void insertPet(Pet pet);
}
