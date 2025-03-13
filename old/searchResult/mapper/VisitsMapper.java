package dev.spring.petclinic.old.searchResult.mapper;

import dev.spring.petclinic.old.searchResult.model.Visits;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VisitsMapper {
    // petId기반으로 Visits List 조회
    List<Visits> selectVisitsByPetId(int petId);
}
