package com.company.authservice.exception.oblect;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ErrorObject {

    private Integer statusCode;

    @NotNull
    @NotBlank
    private String message;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private Date timestamp;

}
