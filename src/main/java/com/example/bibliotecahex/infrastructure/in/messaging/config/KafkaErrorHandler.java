package com.mitocode.qrpayment.infrastructure.in.messaging.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaErrorHandler implements CommonErrorHandler {

    @Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        log.error("Error processing Kafka message - Topic: {}, Partition: {}, Offset: {}, Key: {}", 
            record.topic(), 
            record.partition(), 
            record.offset(), 
            record.key(), 
            exception);
        
        // Aquí podrías implementar lógica adicional como:
        // - Enviar a un Dead Letter Topic
        // - Notificar a sistemas de monitoreo
        // - Implementar retry logic
        
        // Retornar true indica que el error fue manejado y no debe hacer retry
        return true;
    }

    @Override
    public void handleOtherException(Exception exception, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        log.error("General error in Kafka consumer", exception);
    }
}