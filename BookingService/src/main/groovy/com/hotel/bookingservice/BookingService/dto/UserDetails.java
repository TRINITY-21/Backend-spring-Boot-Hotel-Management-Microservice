package com.hotel.bookingservice.BookingService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetails {
    private Long id;
    private String username;
    private String phone;
    private String email;
    private String role;
    private String address;
    private String city;
    private String profileImage;


    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("profileImage")
    public String profileImage() {
        return profileImage;
    }

    @JsonProperty("profileImage")
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }


    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
