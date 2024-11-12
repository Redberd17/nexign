package ru.nefedova.nexign.common.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.nefedova.nexign.common.enumeration.ResCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        List<Map<String, List<String>>> formErrors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            Map<String, List<String>> fieldErrorMap = formErrors.stream()
                    .filter((errorMap) -> errorMap.containsKey(fieldName))
                    .findFirst()
                    .orElseGet(() -> {
                        Map<String, List<String>> newErrorMap = new HashMap<>();
                        newErrorMap.put(fieldName, new ArrayList<>());
                        formErrors.add(newErrorMap);
                        return newErrorMap;
                    });
            (fieldErrorMap.computeIfAbsent(fieldName, (key) -> new ArrayList<>())).add(errorMessage);
        });
        log.error("Ошибка валидации объекта {} : {}", e.getTarget(), formErrors);

        ResponseEntity<ExceptionResponse> response = ResponseEntity.of(Optional.of(
                new ExceptionResponse(
                        ResCode.VALIDATION_ERROR.getCode(),
                        ResCode.VALIDATION_ERROR.name(),
                        "Ошибка валидации " + formErrors
                )
        ));

        return super.handleExceptionInternal(e, response, headers, HttpStatus.OK, request);
    }


    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(EntryNotFoundException exception) {
        log.error(
                "handleEntryNotFoundException: resCode {}, userMessage: {}",
                exception.getResCode(),
                exception.getUserMessage()
        );
        return ResponseEntity.of(Optional.of(
                new ExceptionResponse(
                        exception.getResCode().getCode(),
                        exception.getResCode().name(),
                        exception.getUserMessage()
                )
        ));
    }

//    @ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException exception) {
//        List<Map<String, List<String>>> formErrors = new ArrayList<>();
//        Map<String, List<String>> errors = exception.getConstraintViolations()
//                .stream()
//                .collect(Collectors.toMap(
//                        (constraintViolation) -> constraintViolation.getPropertyPath()
//                                .toString()
//                                .split("\\.")[1],
//                        (constraintViolation) -> Collections.singletonList(constraintViolation.getMessage())
//                ));
//
//        formErrors.add(errors);
//
//        log.error("Ошибка валидации: {}", errors, exception);
//
//        return ResponseEntity.of(Optional.of(
//                new ExceptionResponse(
//                        ResCode.VALIDATION_ERROR.getCode(),
//                        ResCode.VALIDATION_ERROR.name(),
//                        "Ошибка валидации " + formErrors
//                )));
//    }


}
