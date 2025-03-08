package dev.spring.petclinic.searchResult.controller;

import dev.spring.petclinic.searchResult.service.SearchResultService;
import dev.spring.petclinic.searchResult.dto.OwnerDto;
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
        OwnerDto owner = searchResultService.findOwner(ownerId);
        model.addAttribute("owner", owner);

        return "owners/ownerDetails";
    }
}
