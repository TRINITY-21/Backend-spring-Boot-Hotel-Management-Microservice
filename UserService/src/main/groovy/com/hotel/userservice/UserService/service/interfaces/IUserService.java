package com.hotel.userservice.UserService.service.interfaces;


import com.hotel.userservice.UserService.constants.UserRole;
import com.hotel.userservice.UserService.dto.BookingDto;
import com.hotel.userservice.UserService.request.LoginRequest;
import com.hotel.userservice.UserService.request.PasswordResetRequest;
import com.hotel.userservice.UserService.response.ResponseData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {

    ResponseData register(MultipartFile profileImage, String username, String password,
                          String city, String address,
                          String phone, String email, UserRole userRole);

    ResponseData login(LoginRequest loginRequest);

    ResponseData getAllUsers();

    ResponseData addUser(MultipartFile profileImage, String username, String password,
                          String city, String address,
                          String phone, String email, UserRole userRole);

    ResponseData getUserBookingHistory(String userId);

    ResponseData deleteUser(String userId);

    ResponseData getUserById(String userId);

    ResponseData getUserInfo(String email);

    ResponseData activateEmailCode(String code);

    ResponseData sendPasswordResetCode(String email);

    ResponseData resetPassword(PasswordResetRequest request);

    ResponseData getEmailByPasswordResetCode(String code);

    ResponseData updateUser(Long userId,MultipartFile profileImage,String username, String email, String city, String address,String phone, UserRole userRole);
}
