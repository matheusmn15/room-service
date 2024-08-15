package br.com.matheusmn.room_service.service;

import br.com.matheusmn.room_service.domain.dto.RoomRequest;
import br.com.matheusmn.room_service.domain.entity.Room;
import br.com.matheusmn.room_service.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository repository;

    @Override
    public void createRoom(RoomRequest request) {

        var room = Room.builder().roomNumber(request.getRoomNumber())
                .roomType(request.getRoomType())
                .capacity(request.getCapacity())
                .pricePerNight(request.getPricePerNight())
                .build();

        repository.save(room);
    }
}
