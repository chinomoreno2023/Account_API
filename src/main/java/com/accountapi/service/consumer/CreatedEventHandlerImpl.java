package com.accountapi.service.consumer;

import com.accountapi.exception.kafka.NonRetryableException;
import com.accountapi.exception.kafka.RetryableException;
import com.accountapi.model.account.Account;
import com.accountapi.model.event.Event;
import com.accountapi.model.event.ProcessedEventEntity;
import com.accountapi.repository.account.AccountRepository;
import com.accountapi.repository.event.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import java.math.BigDecimal;

@Slf4j
@Component
@KafkaListener(topics = "account-events-topic")
@RequiredArgsConstructor
public class CreatedEventHandlerImpl implements CreatedEventHandler{
    private final ProcessedEventRepository processedEventRepository;
    private final AccountRepository accountRepository;

    @Override
    @KafkaHandler
    @Transactional(transactionManager = "transactionManager")
    public void handle(@Payload Event event, @Header("messageId") String messageId) {
        log.info("Received event: {}", event.getEventId());
        log.info("Message id: {}", messageId);

        ProcessedEventEntity processedEventEntity = processedEventRepository.findByMessageId(messageId);
        if (processedEventEntity != null) {
            log.info("Duplicate message id: {}", messageId);
            return;
        }

        Account from = accountRepository.findByUserId(event.getFrom()).get();
        Account to = accountRepository.findByUserId(event.getTo()).get();
        BigDecimal value = event.getValue();
        try {
            from.setBalance(from.getBalance().subtract(value));
            to.setBalance(to.getBalance().add(value));
        } catch (ResourceAccessException exception) {
            log.error("Network issue while saving wallet: {}", exception.getMessage());
            throw new RetryableException(exception);
        } catch (Exception exception) {
            log.error("Unexpected error while saving wallet: {}", exception.getMessage());
            throw new NonRetryableException(exception);
        }

        try {
            processedEventRepository.save(new ProcessedEventEntity(messageId, event.getEventId()));
        } catch (DataIntegrityViolationException exception) {
            log.error("Data integrity violation while saving processed event: {}", exception.getMessage());
            throw new NonRetryableException(exception);
        }
    }
}
