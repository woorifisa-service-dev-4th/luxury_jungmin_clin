package dev.spring.petclinic.controller.vet;

import dev.spring.petclinic.service.vet.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping("/vets")
    public String getVetList(Model model) {
        model.addAttribute("listVets", vetService.getAllVets());
        return "vets/vetList";
    }
}
