package dev.spring.petclinic.createPet.service;

import dev.spring.petclinic.searchResult.dto.PetDto;
import dev.spring.petclinic.searchResult.mapper.PetsMapper;
import dev.spring.petclinic.searchResult.model.Pets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePetService {

    private final PetsMapper petsMapper;

    /**
     * Pet 등록
     */
    public void createPet(PetDto petDto) {
        Pets pet = convertToPetDto(petDto);
        petsMapper.insertPet(pet);
    }

    /**
     * PetDto -> Pets 변환
     */
    private Pets convertToPetDto(PetDto petDto) {
        return Pets.builder()
                .id(petDto.getId())
                .name(petDto.getName())
                .birthDate(petDto.getBirthDate())
                .typeId(1) // 임시 타입값 할당
                .ownerId(petDto.getOwnerId())
                .build();
    }
}
