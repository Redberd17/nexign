package ru.nefedova.nexign.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private int resCode;
    private String resName;
    private String userMessage;

}
