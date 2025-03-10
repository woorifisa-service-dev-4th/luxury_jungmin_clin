package dev.spring.petclinic.controller.owner;

import dev.spring.petclinic.dto.owner.OwnerDto;
import dev.spring.petclinic.dto.owner.OwnerWithPetsDto;
import dev.spring.petclinic.service.owner.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    // 신규 Owner 등록 폼
    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("ownerDto", OwnerDto.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    // 신규 Owner 등록 처리
    @PostMapping("/new")
    public String processCreationForm(@Valid @ModelAttribute("ownerDto") OwnerDto ownerDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        if (ownerService.isUserExists(ownerDto.getTelephone())) {
            bindingResult.rejectValue("telephone", "duplicate", "이미 존재하는 전화번호입니다.");
            return "owners/createOrUpdateOwnerForm";
        }
        int newOwnerId = ownerService.createOwner(ownerDto);
        redirectAttributes.addFlashAttribute("message", "Owner 생성이 성공적으로 완료되었습니다.");
        return "redirect:/owners/" + newOwnerId;
    }

    // 기존 Owner 수정 폼 (id는 숫자만 허용)
    @GetMapping("/{id:\\d+}/edit")
    public String initUpdateForm(@PathVariable("id") Integer id, Model model) {
        OwnerDto ownerDto = ownerService.findOwnerById(id);
        model.addAttribute("ownerDto", ownerDto);
        return "owners/createOrUpdateOwnerForm";
    }

    // 기존 Owner 수정 처리
    @PostMapping("/{id:\\d+}/edit")
    public String processUpdateForm(@PathVariable("id") Integer id,
            @Valid @ModelAttribute("ownerDto") OwnerDto ownerDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        if (ownerService.isUserExistsForUpdate(id, ownerDto.getTelephone())) {
            bindingResult.rejectValue("telephone", "duplicate", "이미 존재하는 전화번호입니다.");
            return "owners/createOrUpdateOwnerForm";
        }
        ownerService.updateOwner(ownerDto.copyWithId(id)); // ✅ `copyWithId()` 사용
        redirectAttributes.addFlashAttribute("message", "Owner 수정이 성공적으로 완료되었습니다.");
        return "redirect:/owners/" + id;
    }

    // Owner 상세보기 (pets 포함; join 조회 사용)
    @GetMapping("/{id:\\d+}")
    public String showOwnerDetails(@PathVariable("id") Integer id, Model model) {
        OwnerWithPetsDto ownerWithPets = ownerService.findOwnerWithPets(id);
        model.addAttribute("owner", ownerWithPets);
        return "owners/ownerDetails";
    }

    // 검색 폼 (검색 화면)
    @GetMapping("/find")
    public String findOwnersPage(Model model) {
        model.addAttribute("owner", OwnerDto.builder().build());
        return "owners/findOwners";
    }

    // 검색 결과 처리: GET /owners?lastName=... (pets 포함)
    @GetMapping
    public String listOwners(@RequestParam(value = "lastName", required = false) String lastName, Model model) {
        List<OwnerWithPetsDto> ownersList;
        if (lastName == null || lastName.trim().isEmpty()) {
            ownersList = ownerService.findAllOwnersWithPets();
        } else {
            ownersList = ownerService.findOwnersByLastNameWithPets(lastName);
        }
        model.addAttribute("listOwners", ownersList);
        return "owners/ownersList";
    }
}
