package dev.spring.petclinic.createowner.controller;

import dev.spring.petclinic.createowner.dto.OwnerEditDTO;
import dev.spring.petclinic.createowner.service.OwnerCreateService;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/owners/{id}/edit")
public class OwnerUpdateController {

    private final OwnerCreateService ownerCreateService;

    public OwnerUpdateController(OwnerCreateService ownerCreateService) {
        this.ownerCreateService = ownerCreateService;
    }

    // GET 요청: 기존 Owner 데이터를 불러와 수정 폼 초기화
    @GetMapping
    public String initUpdateForm(@PathVariable("id") Integer id, Model model) {
        OwnerEditDTO ownerEditDTO = ownerCreateService.findOwnerById(id);
        model.addAttribute("ownerEditDTO", ownerEditDTO);
        return "owners/createOrUpdateOwnerForm";
    }

    // POST 요청: 수정 처리 (컨트롤러에서 직접 검증)
    @PostMapping
    public String processUpdateForm(@PathVariable("id") Integer id,
            @Valid @ModelAttribute("ownerEditDTO") OwnerEditDTO ownerEditDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }

        if (ownerCreateService.isUserExistsForUpdate(id, ownerEditDTO.getTelephone())) {
            bindingResult.rejectValue("telephone", "duplicate", "이미 존재하는 전화번호입니다.");
            return "owners/createOrUpdateOwnerForm";
        }

        ownerCreateService.updateOwner(ownerEditDTO.withId(id)); // ✅ 기존 ID 유지하면서 업데이트
        redirectAttributes.addFlashAttribute("message", "Owner 수정이 성공적으로 완료되었습니다.");
        return "redirect:/owners/" + id;
    }
}
