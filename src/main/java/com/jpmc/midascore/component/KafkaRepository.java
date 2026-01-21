package com.jpmc.midascore.component;
import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaRepository {
    private static final Logger logger = LoggerFactory.getLogger(KafkaRepository.class);

    @KafkaListener(topics = "${general.kafka-topic}")
    public void listen(Transaction transaction) {
        // We are changing this line to make the amount easy to find in the logs
        logger.info("MY_TRANSACTION_AMOUNT: {}", transaction.getAmount());
    }
}
