package dev.spring.petclinic.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BaseResponseDTO<T> {
    int code;
    String message;
    T data;
}
