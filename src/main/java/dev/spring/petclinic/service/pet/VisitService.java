package dev.spring.petclinic.service.pet;

import dev.spring.petclinic.dto.pet.VisitDto;
import dev.spring.petclinic.mapper.pet.VisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitMapper visitMapper;

    @Transactional
    public void createVisit(VisitDto visitDto) {
        visitMapper.insertVisit(visitDto.toEntity());
    }
}
