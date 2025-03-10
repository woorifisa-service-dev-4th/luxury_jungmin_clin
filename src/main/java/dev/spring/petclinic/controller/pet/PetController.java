package dev.spring.petclinic.controller.pet;

import dev.spring.petclinic.dto.pet.PetDto;
import dev.spring.petclinic.service.pet.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

    private final PetService petService;

    // Pet 등록 폼
    @GetMapping("/new")
    public String createForm(@PathVariable("ownerId") int ownerId, Model model) {
        model.addAttribute("petDto", PetDto.builder().ownerId(ownerId).build());
        return "pets/createOrUpdatePetForm";
    }

    // Pet 등록 처리
    @PostMapping("/new")
    public String processCreationForm(@PathVariable("ownerId") int ownerId,
            @ModelAttribute("petDto") PetDto petDto) {
        PetDto.builder().ownerId(ownerId);
        petService.createPet(petDto);
        return "redirect:/owners/" + ownerId;
    }
}
