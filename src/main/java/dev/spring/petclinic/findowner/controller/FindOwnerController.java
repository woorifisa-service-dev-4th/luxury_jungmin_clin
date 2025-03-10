package dev.spring.petclinic.findowner.controller;

import dev.spring.petclinic.findowner.model.FindOwners;  // Owners 클래스를 import 해야 함
import dev.spring.petclinic.findowner.service.FindOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class FindOwnerController {

    @Autowired
    private final FindOwnerService findOwnerService;

    // 검색 메인 페이지
    @GetMapping("/owners/find")
    public String findOwnersPage(Model model) {
        model.addAttribute("owner", new FindOwners());  // owner 객체를 모델에 추가
        return "owners/findOwners"; // findOwners.html로 이동
    }

    // 검색 결과 페이지 (onwersList.html)
    @GetMapping("/owners")
    public String searchOwners(@RequestParam(value = "lastName", required = false) String lastName, Model model) {
        List<FindOwners> findOwnersList;

        if (lastName == null || lastName.trim().isEmpty()) {
            // 검색어가 없으면 모든 Owner 목록 조회
            findOwnersList = findOwnerService.findAllOwners();
        } else {
            // 검색어가 있으면 성(lastName)을 기준으로 검색
            findOwnersList = findOwnerService.findOwnersByLastName(lastName);
        }

        // 주인 목록 데이터를 모델에 추가
        model.addAttribute("listOwners", findOwnersList);

        return "owners/ownersList"; // ownersList.html로 이동
    }
}
