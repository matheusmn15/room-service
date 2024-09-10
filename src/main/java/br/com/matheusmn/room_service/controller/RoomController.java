package br.com.matheusmn.room_service.controller;

import br.com.matheusmn.room_service.domain.dto.ReservationRequest;
import br.com.matheusmn.room_service.domain.dto.RoomDtoRequest;
import br.com.matheusmn.room_service.domain.dto.RoomDtoResponse;
import br.com.matheusmn.room_service.excepition.RoomNotAvailableException;
import br.com.matheusmn.room_service.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Room endpoint")
@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @Operation(
            summary = "Create a new room",
            description = "Creates a new room with the provided details.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Room created successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    void createRoom(@Valid @RequestBody RoomDtoRequest request) {
        service.createRoom(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateRoom(@PathVariable("id") Long id, @RequestBody RoomDtoRequest request) {
        service.updateRoom(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteRoom(@PathVariable("id") Long id) {
        service.deleteRoom(id);
    }

    @Operation(
            summary = "List all rooms",
            description = "Retrieves a list of all rooms.",
            responses = {@ApiResponse(responseCode = "200", description = "Rooms listed successfully")})
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<RoomDtoResponse> listAllRooms() {
        return service.listAllRooms();
    }

    @Operation(
            summary = "Get room details",
            description = "Retrieves details of a specific room by its ID.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Room details retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "Room not found")
            })
    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    RoomDtoResponse getRoomDetails(@PathVariable("roomId") Long roomId) {
        return service.getRoomDetails(roomId);
    }

    @Operation(
            summary = "List available rooms",
            description = "Retrieves a list of all available rooms.",
            responses = {@ApiResponse(responseCode = "200", description = "Available rooms listed successfully")})
    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    List<RoomDtoResponse> listAvailableRooms() {
        return service.listAvailableRooms();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reserve")
    public ResponseEntity<String> reserveRoom(@RequestBody ReservationRequest reservationRequest)
            throws RoomNotAvailableException {
        String reservationId = service.reserveRoom(reservationRequest);
        return ResponseEntity.ok("Reserva realizada com sucesso! ID da reserva: " + reservationId);
    }
}
