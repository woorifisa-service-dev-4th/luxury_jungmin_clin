package dev.spring.petclinic.findowner.mapper;

import dev.spring.petclinic.findowner.model.Owners;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

// OwnersMapper.xml에서 정의한 SQL을 실행함
@Mapper
public interface OwnersMapper {
    // 특정 Owener를 id로 조회하기
    Owners findOwnerById(@Param("id") Long id);

    // lastname을 기준으로 Owner 조회하기
    List<Owners> findOwnersByLastName(String lastName);

    // 모든 Owner 목록 조회하기
    List<Owners> findAllOwners();
}

