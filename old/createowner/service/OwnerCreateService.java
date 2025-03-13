package dev.spring.petclinic.old.createowner.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.spring.petclinic.old.createowner.dto.OwnerEditDTO;
import dev.spring.petclinic.old.createowner.entity.Owner;
import dev.spring.petclinic.old.createowner.mapper.OwnerMapper;
import dev.spring.petclinic.old.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerCreateService {

    private final OwnerMapper ownerMapper;

    // Owner 등록
    @Transactional
    public int createOwner(OwnerEditDTO ownerEditDTO) {
        Owner owner = ownerEditDTO.toEntity();
        ownerMapper.insertOwner(owner);
        return owner.getId();
    }

    // Owner 수정
    @Transactional
    public void updateOwner(OwnerEditDTO ownerEditDTO) {
        int updated = ownerMapper.updateOwnerById(ownerEditDTO.toEntity());
        if (updated == 0) {
            throw new IllegalStateException("업데이트에 실패하였습니다.");
        }
    }

    // ID로 Owner 조회
    @Transactional(readOnly = true)
    public OwnerEditDTO findOwnerById(Integer id) {
        Owner owner = ownerMapper.findById(id);
        if (owner == null) {
            throw new NotFoundException("존재하지 않는 Owner입니다.");
        }
        return OwnerEditDTO.fromEntity(owner);
    }

    // 신규 등록 시 중복 전화번호 체크
    @Transactional(readOnly = true)
    public boolean isUserExists(String telephone) {
        return ownerMapper.existsByPhoneNumber(telephone);
    }

    // 업데이트 시 중복 전화번호 체크 (현재 Owner의 전화번호는 제외)
    @Transactional(readOnly = true)
    public boolean isUserExistsForUpdate(Integer id, String telephone) {
        Owner existingOwner = ownerMapper.findByTelephone(telephone);
        return existingOwner != null && !existingOwner.getId().equals(id);
    }
}
