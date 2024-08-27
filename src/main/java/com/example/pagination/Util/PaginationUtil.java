package com.example.pagination.Util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaginationUtil {
    //이 메서드는 Page 객체를 매개변수로 받아 페이지네이션 정보를 담은 Map<String, Integer>를 반환
    public static Map<String, Integer> Pagination(Page<?> page) {
        //현재 페이지 번호를 나타냅니다. page.getNumber()+1을 통해 0-based 인덱스를 1-based로 변환
        int currentPage = page.getNumber()+1;
        //전체 페이지의 수
        int totalPages = page.getTotalPages();
        //페이지 블록의 크기, 한 번에 표시되는 페이지 번호의 범위를 제한하는 데 사용됩니다.
        int blockLimit = 10;

        Map<String, Integer> pageInfo = new HashMap<>();
        //페이지 블록의 시작 페이지 번호를 계산
        //currentPage - blockLimit / 2를 통해 페이지 블록 중앙을 현재 페이지 주변으로 설정
        int startPage = Math.max(1, currentPage - blockLimit / 2);
        //페이지 블록의 끝 페이지 번호를 계산
        //startPage + blockLimit - 1를 통해 페이지 블록의 크기만큼의 범위로 설정하되,
        //전체 페이지 수를 초과하지 않도록 조정합니다.
        int endPage = Math.min(startPage + blockLimit - 1, totalPages);
        //현재 페이지의 이전 페이지 번호를 계산
        int prevPage = Math.max(1, currentPage - 1);
        //현재 페이지의 다음 페이지 번호를 계산
        int nextPage = Math.min(totalPages, currentPage + 1);
        //전체 페이지의 마지막 페이지 번호
        int lastPage = totalPages;

        //계산된 정보를 pageInfo 맵에 담아 반환
        pageInfo.put("startPage", startPage);
        pageInfo.put("endPage", endPage);
        pageInfo.put("prevPage", prevPage);
        pageInfo.put("currentPage", currentPage);
        pageInfo.put("nextPage", nextPage);
        pageInfo.put("lastPage", lastPage);

        return pageInfo;
    }
}