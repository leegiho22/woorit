package com.example.pagination.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Builder
public class PageDTO {
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime moddate;
} 