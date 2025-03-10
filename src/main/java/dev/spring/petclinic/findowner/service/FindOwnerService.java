package dev.spring.petclinic.findowner.service;

import dev.spring.petclinic.findowner.mapper.OwnersMapper;
import dev.spring.petclinic.findowner.model.Owners;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindOwnerService {

    private final OwnersMapper ownersMapper;

    // 생성자 주입 방식
    public FindOwnerService(OwnersMapper ownersMapper) {
        this.ownersMapper = ownersMapper;
    }

    public List<Owners> findOwnersByLastName(String lastName) {
        return ownersMapper.findOwnersByLastName(lastName);
    }

    public List<Owners> findAllOwners() {
        return ownersMapper.findAllOwners();
    }
}

