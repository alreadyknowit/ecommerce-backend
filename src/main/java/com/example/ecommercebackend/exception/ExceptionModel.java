package com.example.ecommercebackend.exception;

import java.time.ZonedDateTime;

public record ExceptionModel(int resultCode, String message, int httpStatusCode, ZonedDateTime zonedDateTime) {

}
