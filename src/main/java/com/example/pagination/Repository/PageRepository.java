package com.example.pagination.Repository;

import com.example.pagination.Entity.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
**기본적인 CRUD 메소드
Save (Create/Update) : 새로운 엔터티를 저장하거나, 기존 엔터티를 업데이트합니다.
 CrudEntity savedCrud = boardEntityRepository.save(board);
FindById (Read) : 주어진 식별자에 해당하는 엔터티를 찾아 반환합니다.
 Optional<CrudEntity> foundCrud = boardEntityRepository.findById(1);
DeleteById (Delete) : 주어진 식별자에 해당하는 엔터티를 삭제합니다.
 boardEntityRepository.deleteById(1);
**쿼리 메소드
메서드 이름에 따른 쿼리 생성
 List<CrudEntity> findBySubject(String subject);
AND 조건
 List<CrudEntity> findBySubjectAndAge(String subject, int age);
OR 조건
 List<CrudEntity> findBySubjectOrAge(String subject, int age);
정렬
 List<CrudEntity> findByAgeOrderBySubjectDesc(int age);
**@Query 어노테이션을 통한 직접 쿼리 정의
 @Query("SELECT m FROM CrudEntity m WHERE m.subject = ?1")
 List<CrudEntity> findCrudsBySubject(String subject);
**native Query 사용
 @Query(value = "SELECT * FROM board_entity WHERE subject = :subject",
 nativeQuery = true)
 List<CrudEntity> findCrudsBySubject(@Param("subject") String subject);
**페이징과 정렬
 페이징
 Page<CrudEntity> findByAge(int age, Pageable pageable);
 정렬
 List<CrudEntity> findByAge(int age, Sort sort);
**삭제 및 업데이터 쿼리 정의
 @Modifying
 @Query("UPDATE CrudEntity m SET m.subject = :newSubject WHERE m.id = :id")
 int updateSubject(@Param("id") int id, @Param("newSubject") String newSubject);
*/
public interface PageRepository  extends JpaRepository<PageEntity, Integer>  {

}