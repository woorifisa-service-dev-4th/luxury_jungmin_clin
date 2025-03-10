package dev.spring.petclinic.findowner.service;

import dev.spring.petclinic.findowner.mapper.FindOwnerMapper;
import dev.spring.petclinic.findowner.model.FindOwners;
import dev.spring.petclinic.findowner.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindOwnerService {

    private final FindOwnerMapper findOwnerMapper;

    // 생성자 주입 방식
    public FindOwnerService(FindOwnerMapper findOwnerMapper) {
        this.findOwnerMapper = findOwnerMapper;
    }

    public List<FindOwners> findOwnersByLastName(String lastName) {
        List<FindOwners> findOwners = findOwnerMapper.findOwnersByLastName(lastName);

        // 각 owner의 id 기반으로 pet list도 넣어주기
        for (FindOwners findOwner : findOwners) {
            List<Pet> pets = findOwnerMapper.findPetsByOwnerId(findOwner.getId());
            findOwner.setPets(pets);
        }

        return findOwnerMapper.findOwnersByLastName(lastName);
    }

    public List<FindOwners> findAllOwners() {
        return findOwnerMapper.findAllOwners();
    }

    public List<Pet> findPetsByOwnerId(int ownerId) {
        return findOwnerMapper.findPetsByOwnerId(ownerId);
    }
}

