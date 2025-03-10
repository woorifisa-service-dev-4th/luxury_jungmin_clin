package dev.spring.petclinic.controller.pet;

import dev.spring.petclinic.dto.pet.VisitDto;
import dev.spring.petclinic.service.pet.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;

    // Visit 등록 폼
    @GetMapping("/new")
    public String createForm(@PathVariable("ownerId") int ownerId,
            @PathVariable("petId") int petId,
            Model model) {
        model.addAttribute("visitDto", VisitDto.builder().petId(petId).build());
        return "pets/createOrUpdateVisitForm";
    }

    // Visit 등록 처리
    @PostMapping("/new")
    public String processCreationForm(@PathVariable("ownerId") int ownerId,
            @PathVariable("petId") int petId,
            @ModelAttribute("visitDto") VisitDto visitDto) {
        visitDto = VisitDto.builder()
                .petId(petId)
                .visitDate(visitDto.getVisitDate())
                .description(visitDto.getDescription())
                .build();
        visitService.createVisit(visitDto);
        return "redirect:/owners/" + ownerId;
    }
}
