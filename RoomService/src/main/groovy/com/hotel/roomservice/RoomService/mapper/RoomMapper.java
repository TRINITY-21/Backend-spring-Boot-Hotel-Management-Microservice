package com.hotel.roomservice.RoomService.mapper;

import com.hotel.roomservice.RoomService.dto.RoomDto;
import com.hotel.roomservice.RoomService.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {


        //in order not to use more resources, we break down the response obj dto's
        public static RoomDto mapRoomEntityToRoomDTO(Room room) {
            RoomDto roomDTO = new RoomDto();

            roomDTO.setId(room.getId());
            roomDTO.setType(room.getType());
            roomDTO.setPrice(room.getPrice());
            roomDTO.setImage(room.getImage());
            roomDTO.setDescription(room.getDescription());
            roomDTO.set_booked(room.is_booked());
            roomDTO.setName(room.getName());
            return roomDTO;
        }

        public static RoomDto mapRoomEntityToRoomDtoPlusBookings(Room room) {
            RoomDto roomDto = mapRoomEntityToRoomDTO(room);
            return roomDto;
        }


    public static List<RoomDto> mapRoomListEntityToRoomListDTO(List<Room> roomList) {
        return roomList.stream().map(RoomMapper::mapRoomEntityToRoomDTO).collect(Collectors.toList());
    }

}
