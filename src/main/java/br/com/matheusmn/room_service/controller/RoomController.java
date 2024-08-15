package br.com.matheusmn.room_service.controller;

import br.com.matheusmn.room_service.domain.dto.RoomRequest;
import br.com.matheusmn.room_service.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Room endpoint")
@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @Operation(summary = "Create a new room",
            description = "Creates a new room with the provided details.",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = RoomRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Room created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    @PostMapping()
    void createRoom(RoomRequest request){
        service.createRoom(request);
    }

    @GetMapping
    String get(){
        return "Hello world";
    }
}
