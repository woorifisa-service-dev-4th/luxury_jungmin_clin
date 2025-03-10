package dev.spring.petclinic.mapper.vet;

import dev.spring.petclinic.model.vet.Vet;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VetMapper {
    List<Vet> findAllVets();
}
