package com.hotel.roomservice.RoomService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Room name is required")
    private String name;

    private String description;

    @NotNull(message = "Room price is required")
    private Double price;

    @NotBlank(message = "Room image is required")
    private String image;

    private String type;

    private boolean is_booked = false;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", booked=" + is_booked +
                '}';
    }

}