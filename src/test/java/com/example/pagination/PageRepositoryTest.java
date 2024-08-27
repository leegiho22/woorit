package com.example.pagination;

import com.example.pagination.Entity.PageEntity;
import com.example.pagination.Repository.PageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PageRepositoryTest {
    @Autowired
    private PageRepository pageRepository;

    //게시판 데이터를 삽입하는 테스트 메서드입니다.
    @Test
    public void PageInsertTest()  {
        for(int i=0; i<10; i++) {
        //데이터값 준비(Entity)
        PageEntity pageEntity = PageEntity.builder()
                .subject("게시물 제목")
                .content("게시물 내용")
                .build();

        System.out.println(pageRepository.save(pageEntity));
       }
    }
    //게시판 데이터를 수정하는 테스트 메서드입니다.
    @Test
    public void PageUpdateTest() {
        //데이터값 준비(Entity)
        PageEntity pageEntity = PageEntity.builder()
                .id(1)
                .subject("수정 게시물")
                .content("내용 수정")
                .build();
        System.out.println(pageRepository.save(pageEntity));
    }
    //모든 게시물 데이터를 검색하는 테스트 메서드입니다.
    @Test
    public void PageListTest() {
        List<PageEntity> list = pageRepository.findAll();
        System.out.println(list.toString());
    }
    //특정 ID에 해당하는 개별 게시물 데이터를 검색하는 테스트 메서드입니다.
    @Test
    public void listOneTest()  {
        Optional<PageEntity> list = pageRepository.findById(1);
        System.out.println(list.toString());
    }
    //특정 ID에 해당하는 게시물 데이터를 삭제하는 테스트 메서드입니다.
    @Test
    public void deleteTest() {
        pageRepository.deleteById(1);
    }
}