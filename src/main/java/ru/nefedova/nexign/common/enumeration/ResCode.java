package ru.nefedova.nexign.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResCode {

    OK(0),
    ENTRY_NOT_FOUND(1),
    VALIDATION_ERROR(2);

    private final Integer code;

}
