package dev.spring.petclinic;


import dev.spring.petclinic.findowner.mapper.FindOwnerMapper;
import dev.spring.petclinic.findowner.model.FindOwners;
import dev.spring.petclinic.findowner.model.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PetclinicApplicationTests {

	@Autowired
    private FindOwnerMapper findOwnerMapper;

    @Test
    void testFindOwnerById() {
        // Given (테스트 데이터: ID가 1인 Owner 조회)
        Long ownerId = 1L;

        // When (쿼리 실행)
        FindOwners owner = findOwnerMapper.findOwnerById(ownerId);

        // Then (검증)
        assertThat(owner).isNotNull();
        assertThat(owner.getFirstName()).isEqualTo("George");
        assertThat(owner.getLastName()).isEqualTo("Franklin");
    }

    @Test
    void testFindOwnersByLastName() {
        // Given (검색할 성)
        String lastName = "Davis";

        // When (쿼리 실행)
        List<FindOwners> owners = findOwnerMapper.findOwnersByLastName(lastName);

        // Then (검증)
        assertThat(owners).isNotEmpty();
        assertThat(owners.size()).isGreaterThanOrEqualTo(1);
        assertThat(owners.get(0).getFirstName()).isEqualTo("Betty");
    }

    @Test
    void testFindPetsByOwnerId() {
        // Given (테스트 데이터: ID가 1인 Owner의 Pet 조회)
        int ownerId = 1;

        // When (쿼리 실행)
        List<Pet> pets = findOwnerMapper.findPetsByOwnerId(ownerId);

        // Then (검증)
        assertThat(pets).isNotEmpty();
        assertThat(pets).hasSize(1);
        assertThat(pets.get(0).getName()).isEqualTo("Leo");
		for (int i=0; i<pets.size(); i++) {
			System.out.println(pets.get(i).getName());
		}
    }
}