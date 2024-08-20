package br.com.matheusmn.room_service.excepition;

import org.springframework.stereotype.Component;

@Component
public class ExceptionMessageUtils {

    public static NotFoundException roomNotFoundException(Long id) {
        var title = "Room not found.";
        var message = String.format("Room with id {%s} do not exist in database.", id);
        return new NotFoundException(title, message);
    }
}
