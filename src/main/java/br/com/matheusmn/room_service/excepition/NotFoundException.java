package br.com.matheusmn.room_service.excepition;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(String title, String detail) {
        super(title, detail, HttpStatus.NOT_FOUND);
    }
}
