package dev.spring.petclinic.mapper;

import dev.spring.petclinic.dto.OwnerDto;
import dev.spring.petclinic.dto.OwnerRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OwnerMapper {

    // 검색 및 페이징을 위한 Owner 목록 조회
    List<OwnerDto> findOwners(@Param("lastName") String lastName,
                              @Param("offset") int offset,
                              @Param("limit") int limit);

    // 특정 Owner 조회
    OwnerDto findOwnerById(Integer id);

    // Owner 등록 (OwnerRequestDTO를 사용)
    int insertOwner(OwnerRequestDTO owner);

    // Owner 업데이트 (id와 OwnerRequestDTO를 파라미터로 전달)
    int updateOwner(@Param("id") Integer id, @Param("owner") OwnerRequestDTO owner);
}
