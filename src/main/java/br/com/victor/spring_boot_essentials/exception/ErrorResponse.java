package br.com.victor.spring_boot_essentials.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ErrorResponse {
    private String message;
    private Integer status;
}
