package dev.spring.petclinic.searchResult.mapper;

import dev.spring.petclinic.searchResult.model.Owners;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnersMapper {
    // ownerId 기반으로 Owner 조회
    Owners selectOwnerById(int id);
}
