package dev.spring.petclinic.mapper.pet;

import dev.spring.petclinic.model.pet.Visit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface VisitMapper {
    // petId로 Visit 조회
    List<Visit> selectVisitsByPetId(@Param("petId") int petId);

    // Visit 등록 (옵션)
    void insertVisit(Visit visit);
}
