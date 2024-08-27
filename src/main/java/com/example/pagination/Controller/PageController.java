package com.example.pagination.Controller;

import com.example.pagination.DTO.PageDTO;
import com.example.pagination.Service.PageService;
import com.example.pagination.Util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import static com.example.pagination.Util.PaginationUtil.*;

@Controller
@RequiredArgsConstructor
@Log
public class PageController {

    private final PageService pageService;

    //새로운 CRUD 데이터를 생성하기 위한 폼을 보여주는 페이지로 이동합니다. 이 페이지에서는
    //Thymeleaf를 사용하여 입력 폼이 구성됩니다.
    @GetMapping("/create")
    public String createForm(Model model) {
        log.info("등록 페이지로 이동....");
        //검증처리시
        model.addAttribute("pageDTO", new PageDTO());

        return "board/insert"; // pageInsert.html 뷰 템플릿을 찾아서 렌더링합니다.
    }

    @PostMapping("/create")
    public String createPage(@ModelAttribute PageDTO pageDTO,
                             RedirectAttributes redirectAttributes) {
        log.info("등록 처리 후 목록페이지로 이동....");
        pageService.pageInsert(pageDTO);
        redirectAttributes.addFlashAttribute("successMessage",
                "저장하였습니다.");
        return "redirect:/list";
    }

    //현재 저장된 모든 CRUD 데이터를 조회하여 목록으로 보여주는 페이지로 이동합니다.
    @GetMapping(value= {"/", "/list"})
    public String getAllPages(@PageableDefault(page=1) Pageable pageable, Model model) {
        log.info("데이터 조회 후 목록페이지로 이동....");
        Page<PageDTO> pageDTOList = pageService.list(pageable);

        Map<String, Integer> pageInfo = Pagination(pageDTOList);

        model.addAllAttributes(pageInfo);
        model.addAttribute("List", pageDTOList);

        return "board/list"; // page-list.html 뷰 템플릿을 찾아서 렌더링합니다.
    }

    @GetMapping("/{Id}")
    public String getPageById(@PathVariable Integer Id, Model model) {
        log.info("데이터 조회 후 상세페이지로 이동....");
        PageDTO pageDTO = pageService.pageRead(Id);

        model.addAttribute("data", pageDTO);
        return "board/detail"; // page-details.html 뷰 템플릿을 찾아서 렌더링합니다.
    }

    @GetMapping("/update/{Id}")
    public String updateForm(@PathVariable Integer Id, Model model) {
        log.info("데이터 조회 후 수정페이지로 이동....");
        PageDTO pageDTO = pageService.pageRead(Id);

        model.addAttribute("data", pageDTO);
        return "board/update"; // update-form.html 뷰 템플릿을 찾아서 렌더링합니다.
    }

    //수정 폼에서 제출된 데이터를 받아와서 PageService를 통해 데이터를 수정하고, 수정이 성공하면
    //"/getAll"로 리다이렉트합니다. 이때, 성공 메시지인 "수정하였습니다."를 Flash Attribute로
    //추가하여 다음 페이지에 전달합니다.
    @PostMapping("/update")
    public String updatePage(@ModelAttribute PageDTO updatedPageDTO,
                             RedirectAttributes redirectAttributes) {
        log.info("수정 처리 후 목록페이지로 이동....");
        pageService.pageUpdate(updatedPageDTO);

        redirectAttributes.addFlashAttribute("successMessage",
                "수정하였습니다.");
        return "redirect:/list";
    }

    //특정 CRUD 데이터를 삭제하고, 삭제가 성공하면 "/getAll"로 리다이렉트합니다. 이때, 성공 메시지인
    //"삭제하였습니다."를 Flash Attribute로 추가하여 다음 페이지에 전달합니다.
    @GetMapping("/delete/{Id}")
    public String deletePage(@PathVariable Integer Id,
                             RedirectAttributes redirectAttributes) {
        log.info("삭제 처리 후 목록페이지로 이동....");
        pageService.pageDelete(Id);

        //redirectAttributes.addFlashAttribute("successMessage",
        //        "삭제하였습니다.");
        return "redirect:/list";
    }
}