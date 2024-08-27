package com.example.pagination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//스프링 부트 애플리케이션을 나타냅니다. 이 어노테이션은 여러 다른 어노테이션의 조합을 간소화하고,
//자동 설정, 컴포넌트 스캔 및 기타 기능을 활성화합니다.
@SpringBootApplication
//JPA 감사(auditing)를 활성화합니다. JPA 감사는 엔터티의 생성 및 수정 시간을 자동으로
//기록하는 데 사용됩니다.
@EnableJpaAuditing
public class PaginationApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaginationApplication.class, args);
    }
}
