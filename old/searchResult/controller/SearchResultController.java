package dev.spring.petclinic.old.searchResult.controller;

import dev.spring.petclinic.old.searchResult.service.SearchResultService;
import dev.spring.petclinic.old.searchResult.dto.OwnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class SearchResultController {

    private final SearchResultService searchResultService;

    /**
     * 검색 결과 상세보기 페이지
     * @param ownerId 조회할 owner id
     *                (ex. /owners/1 -> ownerId = 1)
     * @param model Model
     * @return owners/ownerDetails.html
     */
    @GetMapping("/owners/{ownerId}")
    public String searchResultDetail(@PathVariable("ownerId") int ownerId, Model model) {
        // ownerId에 해당하는 owner 정보 조회
        OwnerDto owners = searchResultService.findOwner(ownerId);
        model.addAttribute("owner", owners);

        return "owners/ownerDetails";
    }
}
