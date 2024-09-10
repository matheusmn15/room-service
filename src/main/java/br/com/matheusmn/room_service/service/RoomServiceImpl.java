package br.com.matheusmn.room_service.service;

import br.com.matheusmn.room_service.domain.dto.ReservationRequest;
import br.com.matheusmn.room_service.domain.dto.RoomDtoRequest;
import br.com.matheusmn.room_service.domain.dto.RoomDtoResponse;
import br.com.matheusmn.room_service.domain.entity.Reservation;
import br.com.matheusmn.room_service.domain.entity.Room;
import br.com.matheusmn.room_service.excepition.ExceptionMessageUtils;
import br.com.matheusmn.room_service.excepition.RoomNotAvailableException;
import br.com.matheusmn.room_service.repository.ReservationRepository;
import br.com.matheusmn.room_service.repository.RoomRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void createRoom(RoomDtoRequest request) {

        var room = Room.builder()
                .roomNumber(request.getRoomNumber())
                .roomType(request.getRoomType())
                .capacity(request.getCapacity())
                .pricePerNight(request.getPricePerNight())
                .available(true)
                .build();

        roomRepository.save(room);
    }

    @Override
    public void updateRoom(Long id, RoomDtoRequest request) {
        var room = roomRepository.findById(id).orElseThrow(() -> ExceptionMessageUtils.roomNotFoundException(id));
        room.setRoomType(request.getRoomType());
        room.setCapacity(request.getCapacity());
        room.setPricePerNight(request.getPricePerNight());
        room.setRoomNumber(request.getRoomNumber());

        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        var room = roomRepository.findById(id).orElseThrow(() -> ExceptionMessageUtils.roomNotFoundException(id));
        roomRepository.delete(room);
    }

    @Override
    public List<RoomDtoResponse> listAllRooms() {
        return roomRepository.findAll().stream()
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
        var room =
                roomRepository.findById(roomId).orElseThrow(() -> ExceptionMessageUtils.roomNotFoundException(roomId));
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
        return roomRepository.findAllByAvailableTrue().stream()
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
    public String reserveRoom(ReservationRequest request) throws RoomNotAvailableException {
        Room room = roomRepository
                .findById(request.getRoomId())
                .orElseThrow(() -> new RoomNotAvailableException("Quarto não encontrado"));

        // Verifica a disponibilidade do quarto para as datas solicitadas
        boolean isAvailable = checkRoomAvailability(room, request.getCheckInDate(), request.getCheckOutDate());

        if (!isAvailable) {
            throw ExceptionMessageUtils.roomNotAvailableException();
        }

        // Cria a reserva no banco de dados
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setUserId(request.getUserId());
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setReservationDate(LocalDate.now());

        reservationRepository.save(reservation);

        // Retorna um ID de confirmação (número da reserva)
        return reservation.getId().toString();
    }

    private boolean checkRoomAvailability(Room room, LocalDate checkIn, LocalDate checkOut) {
        // Busca no banco de dados reservas conflitantes para o quarto e período solicitado
        List<Reservation> conflictingReservations =
                reservationRepository.findByRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
                        room.getId(), checkOut, checkIn);

        // Se a lista de reservas conflitantes estiver vazia, o quarto está disponível
        return conflictingReservations.isEmpty();
    }
}
