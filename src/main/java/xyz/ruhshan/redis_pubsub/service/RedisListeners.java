package xyz.ruhshan.redis_pubsub.service;

import org.springframework.stereotype.Service;
import xyz.ruhshan.redis_pubsub.event.PaymentEvent;
import xyz.ruhshan.redis_pubsub.redis_listener.RedisListener;
import xyz.ruhshan.redis_pubsub.event.OrderEvent;

import static xyz.ruhshan.redis_pubsub.configuration.Topics.ORDER_TOPIC;
import static xyz.ruhshan.redis_pubsub.configuration.Topics.PAYMENT_TOPIC;

@Service
public class RedisListeners {

    @RedisListener(topic = ORDER_TOPIC)
    public void orderListener(OrderEvent orderEvent){

        System.out.println("Order Event Received");
    }

    @RedisListener(topic = PAYMENT_TOPIC)
    public void paymentListener(PaymentEvent paymentEvent){

        System.out.println("Payment Event Received");
    }
}
