package xyz.ruhshan.redis_pubsub.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import xyz.ruhshan.redis_pubsub.redis_listener.ListenerScanner;
import xyz.ruhshan.redis_pubsub.service.MasterListener;

import java.util.List;


@Configuration
public class RedisListenerConfig {

    private final MasterListener masterListener;

    public RedisListenerConfig(MasterListener masterListener) {
        this.masterListener = masterListener;
    }


    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        List<ChannelTopic> subscribedTopics = ListenerScanner.getTopics().stream().map(ChannelTopic::of).toList();
        container.addMessageListener(masterListener, subscribedTopics);
        return container;
    }

}
