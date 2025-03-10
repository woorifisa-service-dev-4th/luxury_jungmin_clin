package dev.spring.petclinic.mapper.owner;

import dev.spring.petclinic.dto.owner.OwnerWithPetsDto;
import dev.spring.petclinic.model.owner.Owner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OwnerMapper {
    int insertOwner(Owner owner);

    Owner findById(Integer id);

    boolean existsByPhoneNumber(String telephone);

    Owner findByTelephone(String telephone);

    int updateOwnerById(Owner owner);

    // 기존 전체 조회 (join 미적용)
    List<Owner> findAll();

    // 기존 lastName 기준 조회 (join 미적용)
    List<Owner> findByLastName(@Param("lastName") String lastName);

    // 단건 join 조회 : Owner와 Pets 정보를 함께 조회
    OwnerWithPetsDto findOwnerWithPets(@Param("id") Integer id);

    // 다건 join 조회 : 전체 Owner 조회 (pets 포함)
    List<OwnerWithPetsDto> findAllWithPets();

    // 다건 join 조회 : lastName 기준 Owner 조회 (pets 포함)
    List<OwnerWithPetsDto> findByLastNameWithPets(@Param("lastName") String lastName);
}
