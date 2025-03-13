package dev.spring.petclinic.controller;

import dev.spring.petclinic.dto.BaseResponseDTO;
import dev.spring.petclinic.dto.OwnerDto;
import dev.spring.petclinic.dto.OwnerRequestDTO;
import dev.spring.petclinic.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // GET /api/owners?lastName=xxx&page=1&size=10
    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<OwnerDto>>> getOwners(
            @RequestParam(required = false) String lastName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<OwnerDto> owners = ownerService.getOwners(lastName, page, size);
        BaseResponseDTO<List<OwnerDto>> response = BaseResponseDTO.<List<OwnerDto>>builder()
                .code(200)
                .message("성공")
                .data(owners)
                .build();
        return ResponseEntity.ok(response);
    }

    // GET /api/owners/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<OwnerDto>> getOwnerById(@PathVariable Integer id) {
        OwnerDto owner = ownerService.getOwnerById(id);
        BaseResponseDTO<OwnerDto> response = BaseResponseDTO.<OwnerDto>builder()
                .code(200)
                .message("성공")
                .data(owner)
                .build();
        return ResponseEntity.ok(response);
    }

    // POST /api/owners (새 Owner 등록)
    @PostMapping
    public ResponseEntity<BaseResponseDTO<OwnerDto>> createOwner(@RequestBody OwnerRequestDTO ownerRequest) {
        OwnerDto owner = ownerService.createOwner(ownerRequest);
        BaseResponseDTO<OwnerDto> response = BaseResponseDTO.<OwnerDto>builder()
                .code(201)
                .message("소유자 등록 성공")
                .data(owner)
                .build();
        return ResponseEntity.status(201).body(response);
    }

    // POST /api/owners/{id} (Owner 업데이트)
    @PostMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<OwnerDto>> updateOwner(@PathVariable Integer id,
                                                                 @RequestBody OwnerRequestDTO ownerRequest) {
        OwnerDto owner = ownerService.updateOwner(id, ownerRequest);
        BaseResponseDTO<OwnerDto> response = BaseResponseDTO.<OwnerDto>builder()
                .code(200)
                .message("소유자 업데이트 성공")
                .data(owner)
                .build();
        return ResponseEntity.ok(response);
    }
}
