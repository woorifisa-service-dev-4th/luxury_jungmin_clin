package dev.spring.petclinic.dto.response;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.ResponseEntity;

@Value
@Builder
public class BaseResponseDTO<T> {
    int code;
    String message;
    T data;

    public static <T> ResponseEntity<BaseResponseDTO<T>> buildResponse(ResponseStatus status, T data) {
        BaseResponseDTO<T> response = BaseResponseDTO.<T>builder()
                .code(status.getCode())
                .message(status.getMessage())
                .data(data)
                .build();
        return ResponseEntity.status(status.getCode()).body(response);
    }
}
