package dev.spring.petclinic.old.searchResult.mapper;

import dev.spring.petclinic.old.searchResult.model.Pets;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetsMapper {
    // ownerId 기반으로 Pets List 조회
    List<Pets> selectPetsByOwnerId(int ownerId);

    // pet 저장
    void insertPet(Pets pet);
}
