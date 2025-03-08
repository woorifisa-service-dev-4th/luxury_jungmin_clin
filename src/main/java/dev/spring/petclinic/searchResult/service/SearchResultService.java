package dev.spring.petclinic.searchResult.service;

import dev.spring.petclinic.searchResult.dto.OwnerDto;
import dev.spring.petclinic.searchResult.dto.PetDto;
import dev.spring.petclinic.searchResult.dto.PetVisitDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchResultService {
    /**
     * Owner 정보 조회
     */
    public OwnerDto findOwner(int ownerId) {
        // 임시 데이터 삽입
        List<PetDto> pets = findPetsByOwner(ownerId);

        return OwnerDto.builder()
                .id(1)
                .firstName("홍")
                .lastName("길동")
                .address("서울시 강남구")
                .city("서울")
                .telephone("010-1234-5678")
                .pets(pets)
                .build();
    }

    /**
     * Pet 정보 조회
     */
    public List<PetDto> findPetsByOwner(int ownerId) {
        // 임시 데이터 삽입
        List<PetVisitDto> pet1Visits = findPetVisitsByPet(1);

        PetDto petDto1 = PetDto.builder()
                .petId(1)
                .ownerId(1)
                .name("초코")
                .birthDate(LocalDate.of(2019, 1, 1))
                .type("개")
                .visits(pet1Visits)
                .build();

        PetDto petDto2 = PetDto.builder()
                .petId(2)
                .ownerId(1)
                .name("마이클")
                .birthDate(LocalDate.of(2020, 2, 2))
                .type("고양이")
                .visits(null)
                .build();

        return List.of(petDto1, petDto2);
    }

    /**
     * Pet Visit 정보 조회
     */
    public List<PetVisitDto> findPetVisitsByPet(int petId) {
        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);

        // 임시 데이터 삽입
        PetVisitDto petVisitDto1 = PetVisitDto.builder()
                .petVisitId(1)
                .petId(1)
                .date(now)
                .description("백신 접종")
                .build();

        PetVisitDto petVisitDto2 = PetVisitDto.builder()
                .petVisitId(2)
                .petId(1)
                .date(yesterday)
                .description("어쩌구 할 일")
                .build();

        return List.of(petVisitDto1, petVisitDto2);
    }
}
