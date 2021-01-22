package by.home.hospital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public String handleApiRequestException(ApiRequestException exeption, Model model) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                exeption.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

//        new  ResponseEntity<>(apiException, badRequest)
        model.addAttribute("apiException",  apiException);
        return "/error/error";
    }

}
