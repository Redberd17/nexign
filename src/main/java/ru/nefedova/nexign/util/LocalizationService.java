package ru.nefedova.nexign.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationService {

    private final ResourceBundleMessageSource source;

    public String getMessage(String code) {
        return source.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object... args) {
        return source.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
