package com.hotel.bookingservice.BookingService.utils;

import java.security.SecureRandom;

import static com.hotel.bookingservice.BookingService.constants.BookingCodeConstants.ALPHANUMERIC_STRING;


public class Utils {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomConfirmationCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }



}
