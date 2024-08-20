package br.com.matheusmn.room_service.excepition.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ErrorResponse {
    private String type;
    private String title;
    private String detail;

    public ErrorResponse(String title, String detail) {
        this.type = "error";
        this.title = title;
        this.detail = detail;
    }
}
