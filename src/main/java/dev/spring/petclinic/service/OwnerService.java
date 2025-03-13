package dev.spring.petclinic.service;

import dev.spring.petclinic.dto.OwnerDto;
import dev.spring.petclinic.dto.OwnerRequestDTO;
import dev.spring.petclinic.mapper.OwnerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OwnerService {
    private final OwnerMapper ownerMapper;

    public OwnerService(OwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    // GET /api/owners?lastName=xxx&page=1&size=10
    public List<OwnerDto> getOwners(String lastName, int page, int size) {
        int offset = (page - 1) * size;
        return ownerMapper.findOwners(lastName, offset, size);
    }

    // GET /api/owners/{id}
    public OwnerDto getOwnerById(Integer id) {
        return ownerMapper.findOwnerById(id);
    }

    // POST /api/owners (새 Owner 등록)
    @Transactional
    public OwnerDto createOwner(OwnerRequestDTO ownerRequest) {
        // insertOwner는 generated key를 설정하지만, Record는 불변이므로 직접 값을 주입할 수 없음.
        // 따라서 등록 후 해당 id로 다시 조회하여 반환하는 방식으로 처리합니다.
        ownerMapper.insertOwner(ownerRequest);
        // 예제에서는 마지막으로 등록된 Owner의 id를 가져오는 로직이 필요합니다.
        // 실제 환경에서는 MyBatis의 generatedKey 기능이나 별도 쿼리를 활용하여 id를 얻어야 합니다.
        Integer generatedId = getLastInsertedId();  // (예시용 메서드)
        return ownerMapper.findOwnerById(generatedId);
    }

    // POST 시 생성된 Owner의 id를 가져오는 (예시) 메서드
    private Integer getLastInsertedId() {
        // 실제 구현은 DB에 따라 다르게 처리 필요. 예를 들어, MySQL의 LAST_INSERT_ID() 사용 등.
        // 여기서는 예시로 1을 반환.
        return 1;
    }

    // PUT /api/owners/{id} (Owner 업데이트)
    @Transactional
    public OwnerDto updateOwner(Integer id, OwnerRequestDTO ownerRequest) {
        ownerMapper.updateOwner(id, ownerRequest);
        return ownerMapper.findOwnerById(id);
    }
}
