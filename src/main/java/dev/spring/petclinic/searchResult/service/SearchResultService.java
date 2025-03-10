package dev.spring.petclinic.searchResult.service;

import dev.spring.petclinic.searchResult.dto.OwnerDto;
import dev.spring.petclinic.searchResult.dto.PetDto;
import dev.spring.petclinic.searchResult.dto.PetVisitDto;
import dev.spring.petclinic.searchResult.model.Owners;
import dev.spring.petclinic.searchResult.model.Pets;
import dev.spring.petclinic.searchResult.model.Visits;
import dev.spring.petclinic.searchResult.mapper.OwnersMapper;
import dev.spring.petclinic.searchResult.mapper.PetsMapper;
import dev.spring.petclinic.searchResult.mapper.VisitsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchResultService {

    private final OwnersMapper ownersMapper;
    private final PetsMapper petsMapper;
    private final VisitsMapper visitsMapper;

    /**
     * Owner 정보 조회
     */
    public OwnerDto findOwner(int ownerId) {
        Owners owners = ownersMapper.selectOwnerById(ownerId);
        List<PetDto> pets = findPetsByOwner(ownerId);

        return OwnerDto.builder()
                .id(owners.getId())
                .firstName(owners.getFirstName())
                .lastName(owners.getLastName())
                .address(owners.getAddress())
                .city(owners.getCity())
                .telephone(owners.getTelephone())
                .pets(pets)
                .build();
    }

    /**
     * Pet 정보 조회
     */
    public List<PetDto> findPetsByOwner(int ownerId) {
        List<Pets> pets = petsMapper.selectPetsByOwnerId(ownerId);

        return PetDto.from(pets, this);
    }

    /**
     * Pet Visit 정보 조회
     */
    public List<PetVisitDto> findPetVisitsByPet(int petId) {
        List<Visits> visits = visitsMapper.selectVisitsByPetId(petId);

        return PetVisitDto.from(visits);
    }
}
