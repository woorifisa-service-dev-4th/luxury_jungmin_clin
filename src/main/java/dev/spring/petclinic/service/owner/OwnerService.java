package dev.spring.petclinic.service.owner;

import dev.spring.petclinic.dto.owner.OwnerDto;
import dev.spring.petclinic.dto.owner.OwnerWithPetsDto;
import dev.spring.petclinic.mapper.owner.OwnerMapper;
import dev.spring.petclinic.model.owner.Owner;
import dev.spring.petclinic.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerMapper ownerMapper;

    // 신규 Owner 등록
    @Transactional
    public int createOwner(OwnerDto ownerDto) {
        Owner owner = ownerDto.toEntity();
        ownerMapper.insertOwner(owner);
        return owner.getId();
    }

    // Owner 수정
    @Transactional
    public void updateOwner(OwnerDto ownerDto) {
        int updated = ownerMapper.updateOwnerById(ownerDto.toEntity());
        if (updated == 0) {
            throw new IllegalStateException("업데이트에 실패하였습니다.");
        }
    }

    // 단순 조회 (OwnerDto로 반환)
    @Transactional(readOnly = true)
    public OwnerDto findOwnerById(Integer id) {
        Owner owner = ownerMapper.findById(id);
        if (owner == null) {
            throw new NotFoundException("존재하지 않는 Owner입니다.");
        }
        return OwnerDto.fromEntity(owner);
    }

    // 중복 전화번호 체크
    @Transactional(readOnly = true)
    public boolean isUserExists(String telephone) {
        return ownerMapper.existsByPhoneNumber(telephone);
    }

    // 업데이트 시 중복 전화번호 체크
    @Transactional(readOnly = true)
    public boolean isUserExistsForUpdate(Integer id, String telephone) {
        Owner existingOwner = ownerMapper.findByTelephone(telephone);
        return existingOwner != null && !existingOwner.getId().equals(id);
    }

    // 전체 Owner 조회 (pets 포함)
    @Transactional(readOnly = true)
    public List<OwnerWithPetsDto> findAllOwnersWithPets() {
        return ownerMapper.findAllWithPets();
    }

    // 성(lastName) 기준 Owner 조회 (pets 포함)
    @Transactional(readOnly = true)
    public List<OwnerWithPetsDto> findOwnersByLastNameWithPets(String lastName) {
        return ownerMapper.findByLastNameWithPets(lastName);
    }

    // Owner와 그 pets 단건 조회 (상세보기용)
    @Transactional(readOnly = true)
    public OwnerWithPetsDto findOwnerWithPets(Integer id) {
        OwnerWithPetsDto ownerWithPets = ownerMapper.findOwnerWithPets(id);
        if (ownerWithPets == null) {
            throw new NotFoundException("해당 Owner와 Pets 정보가 존재하지 않습니다.");
        }
        return ownerWithPets;
    }
}
