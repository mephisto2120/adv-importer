package com.tryton.adv.importer.service;

import com.tryton.adv.importer.model.Advertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RetryableAdvertisementWriterTest {

    private static final int MAX_RETRY_ATTEMPTS = 3;

    @Mock
    private AdvertisementWriter advertisementWriterMock;

    private RetryableAdvertisementWriter retryableAdvertisementWriter;

    @BeforeEach
    void setUp() {
        retryableAdvertisementWriter = new RetryableAdvertisementWriter(advertisementWriterMock, retryTemplate());
    }

    @Test
    void shouldRetryWriting() {
        //given
        Advertisement advertisement = new Advertisement();
        doThrow(new RuntimeException("Problem with writing data")).when(advertisementWriterMock).write(advertisement);

        //when
        catchThrowable(() -> retryableAdvertisementWriter.write(advertisement));

        //then
        then(advertisementWriterMock).should(times(MAX_RETRY_ATTEMPTS)).write(advertisement);
    }

    private static RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(10L);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(MAX_RETRY_ATTEMPTS);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
}
