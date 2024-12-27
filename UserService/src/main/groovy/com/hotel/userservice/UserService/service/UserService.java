package com.hotel.userservice.UserService.service;


import com.hotel.userservice.UserService.exception.BaseException;
import com.hotel.userservice.UserService.feign.BookingRestClient;
import com.hotel.userservice.UserService.feign.RoomRestClient;
import com.hotel.userservice.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws BaseException {
        UserDetails userDetails =  userRepository.findByEmail(username);

        if (userDetails == null) {
            throw new BaseException("Username/Email not Found");
        }
        return userDetails;
    }


}

