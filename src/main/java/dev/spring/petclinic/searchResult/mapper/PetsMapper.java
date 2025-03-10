package dev.spring.petclinic.searchResult.mapper;

import dev.spring.petclinic.searchResult.model.Pets;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetsMapper {
    // ownerId 기반으로 Pets List 조회
    List<Pets> selectPetsByOwnerId(int ownerId);
}
