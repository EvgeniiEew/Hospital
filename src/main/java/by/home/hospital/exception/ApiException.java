package by.home.hospital.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private  final String massage;
    private  final HttpStatus httpStatus;
    private  final ZonedDateTime zonedDateTime;

    public ApiException(String massage,
                        HttpStatus httpStatus,
                        ZonedDateTime zonedDateTime) {
        this.massage = massage;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public String getMassage() {
        return massage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
