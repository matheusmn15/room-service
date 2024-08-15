package br.com.matheusmn.room_service.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    private String roomNumber;
    private String roomType;
    private Integer capacity;
    private Double pricePerNight;
}
