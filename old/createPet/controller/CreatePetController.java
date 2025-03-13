package dev.spring.petclinic.old.createPet.controller;

import dev.spring.petclinic.old.createPet.service.CreatePetService;
import dev.spring.petclinic.old.searchResult.dto.OwnerDto;
import dev.spring.petclinic.old.searchResult.dto.PetDto;
import dev.spring.petclinic.old.searchResult.service.SearchResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners/{ownerId}/pets")
public class CreatePetController {
    private final SearchResultService searchResultService;
    private final CreatePetService createPetService;

    @ModelAttribute("owners")
    public OwnerDto findOwner(@PathVariable("ownerId") int ownerId) {
        return searchResultService.findOwner(ownerId);
    }

    /**
     * 펫 등록 페이지
     */
    @GetMapping("/new")
    public String createForm(@ModelAttribute("owners") OwnerDto owners, Model model) {
        model.addAttribute("pet", new PetDto());
        model.addAttribute("isNew", true);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/new")
    public String processCreationFrom(@ModelAttribute("owners") OwnerDto owners,
                                      @ModelAttribute("pet") PetDto pet,
                                      @PathVariable("ownerId") int ownerId) {
        pet.assignOwner(ownerId);
        createPetService.createPet(pet);
        return "redirect:/owners/" + owners.getId();
    }
}
