package com.accountapi.service.producer;

import com.accountapi.exception.kafka.KafkaSendException;
import com.accountapi.model.event.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {
    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Override
    @Transactional
    public String createEvent(Event event) {
        ProducerRecord<String, Event> record = new ProducerRecord<>("account-events-topic",
                event.getFrom().toString(), event);
        record.headers().add("messageId", UUID.randomUUID().toString().getBytes());

        SendResult<String, Event> result = null;
        try {
            result = kafkaTemplate.send(record).get();
        } catch (InterruptedException ex) {
            log.error("Kafka producer was interrupted", ex);
            Thread.currentThread().interrupt();
            throw new KafkaSendException("Kafka producer was interrupted", ex);
        } catch (ExecutionException ex) {
            log.error("Kafka producer failed", ex);
            throw new KafkaSendException("Kafka producer failed", ex);
        }

        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Offset: {}", result.getRecordMetadata().offset());
        log.info("Return: {}", event.getEventId());
        return event.getEventId();
    }
}