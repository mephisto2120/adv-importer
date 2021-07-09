package com.tryton.adv.importer.service;

import com.tryton.adv.importer.model.Advertisement;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@CommonsLog
@Component
public class RetryableAdvertisementWriter implements AdvertisementWriter {

    private final AdvertisementWriter advertisementWriter;
    private final RetryTemplate retryTemplate;

    @Autowired
    public RetryableAdvertisementWriter(
            @Qualifier("standardAdvertisementWriter") AdvertisementWriter advertisementWriter,
            RetryTemplate retryTemplate) {
        this.advertisementWriter = requireNonNull(advertisementWriter);
        this.retryTemplate = requireNonNull(retryTemplate);
    }

    @Override
    public void write(Advertisement advertisement) {
        RetryCallback<Void, RuntimeException> retryCallback = context -> {
            log.info(format("Attempt: %d of saving advertisement ", context.getRetryCount() + 1, advertisement));
            advertisementWriter.write(advertisement);
            return null;
        };

        retryTemplate.execute(retryCallback);
    }
}
