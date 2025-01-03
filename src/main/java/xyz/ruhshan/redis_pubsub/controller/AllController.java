package xyz.ruhshan.redis_pubsub.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ruhshan.redis_pubsub.event.OrderEvent;
import xyz.ruhshan.redis_pubsub.event.PaymentEvent;
import xyz.ruhshan.redis_pubsub.service.RedisMessagePublisher;

@RestController
@RequestMapping("/api")
public class AllController {
    private final RedisMessagePublisher redisMessagePublisher;

    public AllController(RedisMessagePublisher redisMessagePublisher) {
        this.redisMessagePublisher = redisMessagePublisher;
    }

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderEvent orderEvent){
        redisMessagePublisher.publishOrder(orderEvent);
    }

    @PostMapping("/payment")
    public void createPayment(@RequestBody PaymentEvent paymentEvent){
        redisMessagePublisher.publishPayment(paymentEvent);
    }

}
