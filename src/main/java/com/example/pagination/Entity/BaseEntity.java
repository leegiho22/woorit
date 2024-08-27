package com.example.pagination.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//Lombok 어노테이션으로, 해당 클래스의 필드에 대한 Getter와 Setter 메서드를 자동으로 생성해줍니다.
//이를 통해 코드를 간결하게 작성할 수 있습니다.
@Getter
@Setter
//이 어노테이션은 해당 클래스가 테이블을 직접 매핑하지 않고, 자식 클래스에게 매핑 정보를 제공하는
//역할을 합니다. 즉,이 클래스의 필드들은 상속을 통해 하위 엔터티에서 공유될 것입니다.
@MappedSuperclass
//이 어노테이션은 JPA 감사를 활성화합니다. AuditingEntityListener.class는 JPA 엔터티에서 발생하는
//감사 이벤트를 처리하는 리스너를 지정합니다.
@EntityListeners(AuditingEntityListener.class)
//추상 클래스로 선언되었으며, 다른 엔터티 클래스들이 이 클래스를 상속받아 공통 필드와 감사 정보를
//상속받을 수 있도록 합니다.
public abstract class BaseEntity {
    //nullable = false는 이 필드가 데이터베이스에서는 null이 아니어야 함을 나타내고,
    //updatable = false는 이 필드는 수정되지 않도록 설정합니다.
    @Column(name="regdate", nullable = false, updatable = false)
    //JPA 감사를 통해 엔터티가 생성될 때 자동으로 날짜와 시간이 기록되도록 합니다.
    @CreatedDate
    private LocalDateTime regdate;

    @Column(name="moddate")
    //JPA 감사를 통해 엔터티가 수정될 때 자동으로 날짜와 시간이 기록되도록 합니다.
    @LastModifiedDate
    private LocalDateTime moddate;
} 