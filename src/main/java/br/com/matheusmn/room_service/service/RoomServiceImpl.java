package br.com.matheusmn.room_service.service;

import br.com.matheusmn.room_service.domain.dto.RoomDtoRequest;
import br.com.matheusmn.room_service.domain.dto.RoomDtoResponse;
import br.com.matheusmn.room_service.domain.entity.Room;
import br.com.matheusmn.room_service.repository.RoomRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository repository;

    @Override
    public void createRoom(RoomDtoRequest request) {

        var room = Room.builder()
                .roomNumber(request.getRoomNumber())
                .roomType(request.getRoomType())
                .capacity(request.getCapacity())
                .pricePerNight(request.getPricePerNight())
                .available(true)
                .build();

        repository.save(room);
    }

    @Override
    public void updateRoom(Long id, RoomDtoRequest request) {
        var room = repository.findById(id).get();
        room.setRoomType(request.getRoomType());
        room.setCapacity(request.getCapacity());
        room.setPricePerNight(request.getPricePerNight());
        room.setRoomNumber(request.getRoomNumber());

        repository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        var room = repository.findById(id).get();
        repository.delete(room);
    }

    @Override
    public List<RoomDtoResponse> listAllRooms() {
        return repository.findAll().stream()
                .map(room -> new RoomDtoResponse(
                        room.getId(),
                        room.getRoomNumber(),
                        room.getRoomType(),
                        room.getCapacity(),
                        room.getPricePerNight(),
                        room.getAvailable()))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDtoResponse getRoomDetails(Long roomId) {
        var room = repository.findById(roomId).get();
        //                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));
        return new RoomDtoResponse(
                room.getId(),
                room.getRoomNumber(),
                room.getRoomType(),
                room.getCapacity(),
                room.getPricePerNight(),
                room.getAvailable());
    }

    @Override
    public List<RoomDtoResponse> listAvailableRooms() {
        return repository.findAllByAvailableTrue().stream()
                .map(room -> new RoomDtoResponse(
                        room.getId(),
                        room.getRoomNumber(),
                        room.getRoomType(),
                        room.getCapacity(),
                        room.getPricePerNight(),
                        room.getAvailable()))
                .collect(Collectors.toList());
    }
}
