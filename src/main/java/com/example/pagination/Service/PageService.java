package com.example.pagination.Service;

import com.example.pagination.DTO.PageDTO;
import com.example.pagination.Entity.PageEntity;
import com.example.pagination.Repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/*
**주요 기능
1. 검증 및 예외 처리 (Validation and Exception Handling):
 입력 데이터의 유효성을 검증하고 예외 처리를 수행합니다.
2. 트랜잭션 관리 (Transaction Management):
 여러 개의 데이터베이스 조작이 하나의 논리적 작업으로 묶이도록 하는 것을 의미합니다.
3. 비즈니스 로직 수행 (Business Logic Execution):
 실제 업무 처리 로직을 수행합니다. CRUD 이외의 복잡한 로직들이 여기에 해당합니다.
4. 보안 관련 기능 (Security-related Features):
 사용자 권한 확인, 접근 제어 등과 같은 보안 관련 기능을 수행합니다.
5. 이메일 발송, 알림 전송 (Email Sending, Notification):
 사용자에게 이메일 발송이나 푸시 알림과 같은 통지 기능을 수행합니다.
6. 외부 서비스와의 통합 (Integration with External Services):
 외부 서비스와의 통합을 위해 API 호출 등의 기능을 수행합니다.
7. 일정한 주기로 수행되는 작업 (Scheduled Tasks):
 일정한 주기로 실행되어야 하는 작업을 정의할 수 있습니다.
 */
//서비스 클래스
@Service
//트랜잭션을 관리하기 위한 어노테이션입니다.
//어노테이션을 추가하면 해당 메서드는 트랜잭션 내에서 실행되며, 메서드 수행 중에 예외가 발생하면
//트랜잭션은 롤백됩니다. 예외가 발생하지 않으면 커밋됩니다.
@Transactional
//Lombok에서 제공하는 어노테이션으로, 필드를 기반으로 생성자를 자동으로 생성해주는 기능을 제공합니다.
//클래스 내의 final이나 @NonNull 어노테이션이 붙은 필드에 대한 생성자를 생성해줍니다.
//이를 통해 생성자 주입(Constructor Injection)을 간편하게 구현할 수 있습니다.
@RequiredArgsConstructor
public class PageService {
    private final PageRepository pageRepository;
    private final ModelMapper modelMapper;

    //삭제
    public void pageDelete(Integer id) {
        //작업은 Repository
        pageRepository.deleteById(id);
    }
    //삽입(보내온 값은 DTO)
    public PageDTO pageInsert(PageDTO pageDTO) {
        PageEntity pageEntity = modelMapper
                .map(pageDTO, PageEntity.class);
        PageEntity result = pageRepository.save(pageEntity); //필요한 값은 Entity

        PageDTO resultDTO = modelMapper.map(result, PageDTO.class);

        return resultDTO;
    }

    //수정(보내온 값을 DTO)
    public PageDTO pageUpdate(PageDTO pageDTO) {
        Optional<PageEntity> findPage =
                pageRepository.findById(pageDTO.getId());

       /* if (findPage.isPresent()) {
            PageEntity existingPage = findPage.get();

            existingPage.setSubject(pageDTO.getSubject());
            existingPage.setContent(pageDTO.getContent());
            PageEntity updatedPage = pageRepository.save(existingCrud);

            PageDTO result = modelMapper.map(updatedPage, PageDTO.class);
            return result;
        }*/
        if(findPage.isPresent()) {
            PageEntity pageEntity = modelMapper.map(pageDTO, PageEntity.class);
            PageEntity result = pageRepository.save(pageEntity);
            PageDTO resultDTO = modelMapper.map(result, PageDTO.class);

            return resultDTO;
        }

        return null;
    }
    //개별조회
    public PageDTO pageRead(Integer id) {
        Optional<PageEntity> pageEntity = pageRepository.findById(id);

        PageDTO pageDTO = modelMapper.map(pageEntity, PageDTO.class);
        return pageDTO;
    }
    //전체조회
    //pageable page(요청페이지)
    public Page<PageDTO> list(Pageable page) {
        //화면에      페이지번호 1, 2, 3, 4, 5, 6
        //데이터베이스 페이지번호 0, 1, 2, 3, 4, 5
        //화면에 페이지번호를 데이터베이스 페이지 번호로 계산
        int currentPage = page.getPageNumber()-1;
        //한 페이지에 읽어올 개수(지정한 페이지에서 읽어올 개수)
        int pageLimits = 10;
        //현재지정한 페이지를 지정한 개수만큼 읽어오는데 id를 기준으로 내림차순
        Pageable pageable = PageRequest.of(currentPage, pageLimits,
                Sort.by(Sort.Direction.DESC,"id"));
        Page<PageEntity> pageEntities = pageRepository.findAll(pageable); //페이지에 대한 조회
        //데이터값 변환(ModelMapper DTO<->Entity), Page에 대한 변환X

        Page<PageDTO> pageDTOS = pageEntities.map(data->modelMapper.map(data,
                PageDTO.class));
        /*Page<PageDTO> pageDTOS = pageEntities.map(
                data->PageDTO.builder()
                        .id(data.getId()).page(data.getPage())
                        .detail(data.getDetail()).price(data.getPrice())
                        .quantity(data.getQuantity())
                        .build()); */
        return pageDTOS;
    }
} 
