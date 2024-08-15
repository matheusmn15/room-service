package br.com.matheusmn.room_service.service;

import br.com.matheusmn.room_service.domain.dto.RoomRequest;

public interface RoomService {

    void createRoom(RoomRequest request);
}
