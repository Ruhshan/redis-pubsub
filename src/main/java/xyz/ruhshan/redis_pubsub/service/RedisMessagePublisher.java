package xyz.ruhshan.redis_pubsub.service;

import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import xyz.ruhshan.redis_pubsub.event.OrderEvent;
import xyz.ruhshan.redis_pubsub.event.PaymentEvent;

import static xyz.ruhshan.redis_pubsub.configuration.Topics.ORDER_TOPIC;
import static xyz.ruhshan.redis_pubsub.configuration.Topics.PAYMENT_TOPIC;

@Service
public class RedisMessagePublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publishOrder(OrderEvent orderEvent){
        redisTemplate.convertAndSend(ORDER_TOPIC, orderEvent);
    }

    public void publishPayment(PaymentEvent paymentEvent){
        redisTemplate.convertAndSend(PAYMENT_TOPIC, paymentEvent);
    }



}
