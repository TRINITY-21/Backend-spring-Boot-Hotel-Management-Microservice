package com.hotel.roomservice.RoomService.repository;

import com.hotel.roomservice.RoomService.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    // queries to use for the search feature
    @Query("SELECT DISTINCT r.type FROM Room r")
    List<String> findDistinctRoomTypes();

}
