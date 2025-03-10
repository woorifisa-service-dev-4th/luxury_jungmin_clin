package dev.spring.petclinic.service.vet;

import dev.spring.petclinic.dto.vet.VetDto;
import dev.spring.petclinic.mapper.vet.VetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetMapper vetMapper;

    @Transactional(readOnly = true)
    public List<VetDto> getAllVets() {
        return vetMapper.findAllVets().stream()
                .map(VetDto::fromEntity)
                .collect(Collectors.toList());
    }
}
