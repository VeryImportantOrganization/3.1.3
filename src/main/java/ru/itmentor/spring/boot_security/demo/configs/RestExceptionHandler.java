package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itmentor.spring.boot_security.demo.dto.ErrorDto;
import ru.itmentor.spring.boot_security.demo.exceptions.DemoAppException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = DemoAppException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(DemoAppException exc) {
        return ResponseEntity
                .status(exc.getStatus())
                .body(ErrorDto.builder().message(exc.getMessage()).build());
    }
}
