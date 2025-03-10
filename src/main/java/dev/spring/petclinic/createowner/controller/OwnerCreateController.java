package dev.spring.petclinic.createowner.controller;

import dev.spring.petclinic.createowner.dto.OwnerDTO;
import dev.spring.petclinic.createowner.service.OwnerCreateService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OwnerCreateController {

    private final OwnerCreateService ownerCreateService;

    public OwnerCreateController(OwnerCreateService ownerCreateService) {
        this.ownerCreateService = ownerCreateService;
    }

    // GET 요청: 신규 Owner 등록 폼 초기화
    @GetMapping("/owners/new")
    public String initCreationForm(Model model) {
        model.addAttribute("ownerDTO", OwnerDTO.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    // POST 요청: 신규 Owner 등록 처리 (자동 검증)
    @PostMapping("/owners/new")
    public String processCreationForm(@Valid @ModelAttribute("ownerDTO") OwnerDTO ownerDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }

        if (ownerCreateService.isUserExists(ownerDTO.getTelephone())) {
            bindingResult.rejectValue("telephone", "duplicate", "이미 존재하는 전화번호입니다.");
            return "owners/createOrUpdateOwnerForm";
        }

        ownerCreateService.createOwner(ownerDTO);
        redirectAttributes.addFlashAttribute("message", "Owner 생성이 성공적으로 완료되었습니다.");
        return "redirect:/owners/" + ownerDTO.getId();
    }
}
