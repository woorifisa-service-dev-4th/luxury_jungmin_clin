package dev.spring.petclinic.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
    SUCCESS(200, "성공"),
    CREATED(201, "소유자 등록 성공"),
    UPDATED(200, "소유자 업데이트 성공"),
    NOT_FOUND(404, "존재하지 않는 리소스"),
    BAD_REQUEST(400, "잘못된 요청"),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류");

    private final int code;
    private final String message;
}
