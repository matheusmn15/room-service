package br.com.matheusmn.room_service.excepition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException {

    private String title;
    private String detail;
    private HttpStatus httpStatus;

    public ApiException(String title, String detail, HttpStatus httpStatus) {
        super(detail);
        this.title = title;
        this.detail = detail;
        this.httpStatus = httpStatus;
    }
}
