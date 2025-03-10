package dev.spring.petclinic.createowner.mapper;

import org.apache.ibatis.annotations.Mapper;
import dev.spring.petclinic.createowner.entity.Owner;

@Mapper
public interface OwnerMapper {
    void insertOwner(Owner owner);

    Owner findById(Integer id);

    boolean existsByPhoneNumber(String telephone);

    // 추가: 전화번호로 Owner 조회
    Owner findByTelephone(String telephone);

    void deleteOwnerById(Integer id);

    int updateOwnerById(Owner owner);
}
