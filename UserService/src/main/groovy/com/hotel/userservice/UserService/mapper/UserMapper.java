package com.hotel.userservice.UserService.mapper;


import com.hotel.userservice.UserService.dto.UserDto;
import com.hotel.userservice.UserService.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapUserEntityToUserDto(User user) {
        UserDto userDto = new UserDto();

        //we ignore the password and bookings
        userDto.setId(user.getId());
        userDto.setUsername(user.getDisplayName());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setCity(user.getCity());
        userDto.setProfileImage(user.getProfileImage());
        userDto.set_deleted(user.is_deleted());
        userDto.setActive(user.isActive());

        return userDto;

    }

    public static UserDto mapUserEntityToUserDtoPlusUserBookingsAndRoom(User user) {
        UserDto userDto = new UserDto();

        //we ignore the password and bookings
        userDto.setId(user.getId());
        userDto.setUsername(user.getDisplayName());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setCity(user.getCity());
        userDto.setProfileImage(user.getProfileImage());

        return userDto;

    }

    public static List<UserDto> mapUserListEntityToUserListDTO(List<User> userList) {
        return userList.stream().map(UserMapper::mapUserEntityToUserDto).collect(Collectors.toList());
    }




}
