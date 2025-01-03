package com.hotel.userservice.UserService.repository;

import com.hotel.userservice.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
    boolean existsByEmail(String email);
    User findByActivationCode(String code);

    @Query("SELECT user.email FROM User user WHERE user.passwordResetCode = :code")
    String getEmailByPasswordResetCode(@Param("code") String code);
}
