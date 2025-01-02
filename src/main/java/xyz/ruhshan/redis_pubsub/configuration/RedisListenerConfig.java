package xyz.ruhshan.redis_pubsub.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import xyz.ruhshan.redis_pubsub.service.OrderEventListener;

import static xyz.ruhshan.redis_pubsub.configuration.Topics.ORDER_TOPIC;

@Configuration
public class RedisListenerConfig {

    private final OrderEventListener orderEventListener;

    public RedisListenerConfig(OrderEventListener orderEventListener) {
        this.orderEventListener = orderEventListener;
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(orderEventListener, ChannelTopic.of(ORDER_TOPIC));
        return container;
    }

}
