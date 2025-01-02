package xyz.ruhshan.redis_pubsub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import xyz.ruhshan.redis_pubsub.event.OrderEvent;

@Service
@Slf4j
public class OrderEventListener implements MessageListener {
    private final ObjectMapper objectMapper;

    public OrderEventListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            OrderEvent orderEvent = objectMapper.readValue(message.getBody(), OrderEvent.class);
            log.info("Received Order Event: {}", orderEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
