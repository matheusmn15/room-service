package br.com.matheusmn.room_service.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoResponse {

    private Long id;
    private String roomNumber;
    private String roomType;
    private Integer capacity;
    private Double pricePerNight;
    private Boolean available;
}
