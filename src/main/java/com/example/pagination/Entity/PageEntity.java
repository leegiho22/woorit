package com.example.pagination.Entity;

/*
@Entity : 해당 클래스가 JPA 엔터티임을 나타내는 어노테이션입니다.
JPA는 이 어노테이션이 있는 클래스를 데이터베이스 테이블과 매핑합니다.
@Table : 데이터베이스 테이블에 대한 추가적인 매핑 정보를 제공하는 어노테이션입니다.
특정 테이블의 이름, 스키마, 인덱스, 고유 제약 조건 등을 설정할 수 있습니다.
@Id : 해당 필드가 엔터티의 식별자(primary key)임을 나타내는 어노테이션입니다.
엔터티 클래스에서는 반드시 식별자 필드에 지정되어야 합니다.
@GeneratedValue : 식별자 필드의 값을 자동으로 생성하는 방법을 지정하는 어노테이션입니다.
주로 시퀀스, 자동 증가 등의 전략을 선택할 수 있습니다.
@SequenceGenerator : 시퀀스 생성기를 정의하는 어노테이션으로, @GeneratedValue에서
사용할 때 해당 시퀀스 생성기를 지정합니다.
@Column : 필드 레벨에서 데이터베이스 컬럼과 매핑을 지정하는 어노테이션입니다.
컬럼의 이름, 길이, null 허용 여부 등을 설정할 수 있습니다.
@ManyToOne : 다대일(Many-to-One) 관계를 나타내는 어노테이션으로, 엔터티 간의 관계를 설정합니다.
다수의 엔터티가 하나의 엔터티에 속하는 경우 사용됩니다.
@OneToMany : 일대다(One-to-Many) 관계를 나타내는 어노테이션으로, 엔터티 간의 관계를 설정합니다.
하나의 엔터티가 다수의 엔터티를 가지는 경우 사용됩니다.
@OneToOne : 일대일(One-to-One) 관계를 나타내는 어노테이션으로, 두 엔터티 간에 일대일 관계를
설정합니다. 특정 엔터티가 다른 엔터티에 대해 하나의 관계를 가질 때 사용됩니다.
@JoinColumn : @ManyToOne 또는 @OneToOne 관계에서 외래 키(Foreign Key)를 정의하는
어노테이션으로, 연결할 컬럼과 외래 키의 속성을 지정합니다.
@Transient : 해당 필드를 영속성 컨텍스트에 저장하지 않고 데이터베이스에 매핑하지 않도록 지정하는
어노테이션입니다. 이 어노테이션이 붙은 필드는 데이터베이스에 영향을 주지 않습니다.
@JoinTable : 다대다(Many-to-Many) 관계를 매핑할 때 사용되는 어노테이션으로, 연결 테이블을
정의합니다. 주로 두 엔터티 간의 관계를 일대다 및 다대일로 변환할 때 사용됩니다.
@OrderBy : 컬렉션 타입인 필드에 사용되며, 엔터티를 검색할 때 정렬 순서를 지정합니다.
정렬 기준을 제공하여 결과를 정렬할 수 있습니다.
*/

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @ToString @Builder
@Entity
//name = "pagination_table": 이 어노테이션은 엔터티 클래스가 매핑되는 데이터베이스 테이블의
//이름을 지정합니다. 여기서는 "board_entity" 테이블과 매핑됩니다.
@Table(name = "pagination_table")
//name = "pagination_sql": 시퀀스 생성기의 이름을 지정합니다.
//sequenceName = "pagination_seq": 데이터베이스에서 사용할 시퀀스의 이름을 지정합니다.
//initialValue = 1: 시퀀스의 초기 값으로 1을 설정합니다.
//allocationSize = 1: 시퀀스에서 한 번에 할당되는 값의 수를 설정합니다.
@SequenceGenerator(
        name = "pagination_seq",
        sequenceName = "pagination_seq",
        initialValue = 1,
        allocationSize = 1
)
public class PageEntity extends BaseEntity {
    //@Id: 해당 필드가 엔터티의 식별자(primary key)임을 나타냅니다.
    //@GeneratedValue: 해당 필드의 값을 자동으로 생성하며, strategy 속성을 통해 어떤 전략으로
    //생성할지를 지정합니다.
    //strategy = GenerationType.SEQUENCE: 시퀀스를 사용하여 값을 생성합니다.
    //generator = "pagination_seq": 앞서 정의한 시퀀스 생성기를 지정합니다.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagination_seq")
    //name = "id": 해당 필드가 매핑되는 데이터베이스 컬럼의 이름을 지정합니다.
    //nullable = false: 해당 필드는 데이터베이스 컬럼에 null 값을 허용하지 않습니다.
    @Column(name = "id", nullable = false)
    private Integer id;

    //name = "username": 해당 필드가 매핑되는 데이터베이스 컬럼의 이름을 지정합니다.
    //nullable = false: 해당 필드는 데이터베이스 컬럼에 null 값을 허용하지 않습니다.
    //length = 50: 해당 필드가 매핑되는 데이터베이스 컬럼의 길이를 지정합니다.
    //여기서는 50으로 지정되어 있습니다.
    @Column(name="subject", nullable = false, length = 50) //생략 불가능, 길이 50
    private String subject;

    @Column(name="content", length = 100)
    private String content;
}