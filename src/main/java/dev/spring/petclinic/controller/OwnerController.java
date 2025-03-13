package dev.spring.petclinic.controller;

import dev.spring.petclinic.dto.OwnerDto;
import dev.spring.petclinic.dto.OwnerRequestDTO;
import dev.spring.petclinic.dto.response.BaseResponseDTO;
import dev.spring.petclinic.dto.response.ResponseStatus;
import dev.spring.petclinic.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<OwnerDto>>> getOwners(
            @RequestParam(required = false) String lastName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<OwnerDto> owners = ownerService.getOwners(lastName, page, size);
        return BaseResponseDTO.buildResponse(ResponseStatus.SUCCESS, owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<OwnerDto>> getOwnerById(@PathVariable Integer id) {
        OwnerDto owner = ownerService.getOwnerById(id);
        return BaseResponseDTO.buildResponse(ResponseStatus.SUCCESS, owner);
    }

    @PostMapping
    public ResponseEntity<BaseResponseDTO<OwnerDto>> createOwner(@RequestBody OwnerRequestDTO ownerRequest) {
        OwnerDto owner = ownerService.createOwner(ownerRequest);
        return BaseResponseDTO.buildResponse(ResponseStatus.CREATED, owner);
    }

    @PostMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<OwnerDto>> updateOwner(@PathVariable Integer id,
                                                                @RequestBody OwnerRequestDTO ownerRequest) {
        OwnerDto owner = ownerService.updateOwner(id, ownerRequest);
        return BaseResponseDTO.buildResponse(ResponseStatus.UPDATED, owner);
    }
}
