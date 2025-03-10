package dev.spring.petclinic.service.pet;

import dev.spring.petclinic.dto.pet.PetDto;
import dev.spring.petclinic.mapper.pet.PetMapper;
import dev.spring.petclinic.mapper.pet.VisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import dev.spring.petclinic.model.pet.Pet;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetMapper petMapper;
    private final VisitMapper visitMapper;

    @Transactional(readOnly = true)
    public List<PetDto> findPetsByOwner(int ownerId) {
        List<Pet> pets = petMapper.selectPetsByOwnerId(ownerId);
        // 각 Pet에 대해 Visit 조회
        return pets.stream().map(pet -> {
            pet = pet.toBuilder().visits(visitMapper.selectVisitsByPetId(pet.getId())).build();
            return PetDto.fromEntity(pet);
        }).collect(Collectors.toList());
    }

    @Transactional
    public void createPet(PetDto petDto) {
        petMapper.insertPet(petDto.toEntity());
    }
}
