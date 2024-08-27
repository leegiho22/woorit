package com.example.pagination;

import com.example.pagination.DTO.PageDTO;
import com.example.pagination.Service.PageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class PageServiceTest {
    @Autowired
    private PageService pageService;

    //게시판 데이터를 삽입하는 테스트 메서드입니다.
    @Test
    public void PageInsertTest()  {
        for(int i=0; i<10; i++) {
            //데이터값 준비(Entity)
            PageDTO pageDTO = PageDTO.builder()
                    .subject("게시물 제목")
                    .content("게시물 내용")
                    .build();

            System.out.println(pageService.pageInsert(pageDTO));
        }
    }
    //게시판 데이터를 수정하는 테스트 메서드입니다.
    @Test
    public void PageUpdateTest() {
        //데이터값 준비(Entity)
        PageDTO pageDTO = PageDTO.builder()
                .id(1)
                .subject("수정 게시물")
                .content("내용 수정")
                .build();
        System.out.println(pageService.pageUpdate(pageDTO));
    }
    //모든 게시물 데이터를 검색하는 테스트 메서드입니다.
    @Test
    public void PageListTest() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<PageDTO> list = pageService.list(pageable);
        System.out.println(list.toString());

    }
    //특정 ID에 해당하는 개별 게시물 데이터를 검색하는 테스트 메서드입니다.
    @Test
    public void listOneTest()  {
        PageDTO list = pageService.pageRead(1);
        System.out.println(list.toString());
    }
    //특정 ID에 해당하는 게시물 데이터를 삭제하는 테스트 메서드입니다.
    @Test
    public void deleteTest() {
        pageService.pageDelete(5);
    }
}