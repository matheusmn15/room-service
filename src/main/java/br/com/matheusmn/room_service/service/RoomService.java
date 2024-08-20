package br.com.matheusmn.room_service.service;

import br.com.matheusmn.room_service.domain.dto.RoomDtoRequest;
import br.com.matheusmn.room_service.domain.dto.RoomDtoResponse;
import java.util.List;

public interface RoomService {

    void createRoom(RoomDtoRequest request);

    void updateRoom(Long id, RoomDtoRequest request);

    void deleteRoom(Long id);

    List<RoomDtoResponse> listAllRooms();

    RoomDtoResponse getRoomDetails(Long roomId);

    List<RoomDtoResponse> listAvailableRooms();
}
