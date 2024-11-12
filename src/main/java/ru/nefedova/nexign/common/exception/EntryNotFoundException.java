package ru.nefedova.nexign.common.exception;

import lombok.Getter;
import ru.nefedova.nexign.common.enumeration.ResCode;

@Getter
public class EntryNotFoundException extends RuntimeException {

    private final ResCode resCode;
    private final String userMessage;

    public EntryNotFoundException(String userMessage) {
        super(userMessage);
        this.resCode = ResCode.ENTRY_NOT_FOUND;
        this.userMessage = userMessage;
    }

}
