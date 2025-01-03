package com.hotel.roomservice.RoomService.controller;

import com.hotel.roomservice.RoomService.constants.Pages;
import com.hotel.roomservice.RoomService.response.ResponseData;
import com.hotel.roomservice.RoomService.service.interfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:7070/")
@RequestMapping(Pages.ROOMS_BASE)
public class RoomController {

    @Autowired
    private IRoomService roomService;


    @PostMapping(Pages.ADD_ROOM)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> addNewRoom(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "name", required = false) String name

    ) {

        if ((image == null) || image.isEmpty() || (type == null) || type.isBlank() || (price == null) ) {
            ResponseData response = new ResponseData();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(photo, roomType,roomPrice)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        ResponseData response = roomService.addNewRoom(image, name, type,price, description);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(Pages.GET_ALL_ROOMS)
    public ResponseEntity<ResponseData> getAllRooms() {
        ResponseData response = roomService.getAllRooms();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping(Pages.GET_ROOM_TYPES)
    public List<String> getRoomTypes() {
        return roomService.getAllRoomTypes();
    }

    @GetMapping(Pages.GET_ROOM_BY_ID)
    public ResponseEntity<ResponseData> getRoomById(@PathVariable("roomId") Long roomId) {
        ResponseData response = roomService.getRoomById(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }



    @PatchMapping(Pages.UPDATE_ROOM)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> updateRoom(@PathVariable("roomId") Long roomId,
                                               @RequestParam(value = "image", required = false) MultipartFile photo,
                                               @RequestParam(value = "type", required = false) String roomType,
                                               @RequestParam(value = "price", required = false) Double roomPrice,
                                               @RequestParam(value = "description", required = false) String roomDescription,
                                               @RequestParam(value = "name", required = false) String  roomName

    ) {
        ResponseData response = roomService.updateRoom(roomId, roomDescription, roomType,roomName, roomPrice, photo);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping(Pages.DELETE_ROOM)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> deleteRoom(@PathVariable("roomId") Long roomId) {
        ResponseData response = roomService.deleteRoom(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }


    // Endpoint to update the booking status of a room
    @PutMapping("/update-booking-status/{roomId}")
    public ResponseData updateBookingStatus(@PathVariable("roomId") Long roomId, @RequestParam("isBooked") boolean isBooked) {
        return roomService.updateBookingStatus(roomId, isBooked);
    }




}
