package com.tacoloco.service.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageByLocaleService {

    private final MessageSource messageSource;

    @Autowired
    public MessageByLocaleService(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(final String id) {
        final Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, null, locale);
    }
}
