package xyz.ruhshan.redis_pubsub.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import xyz.ruhshan.redis_pubsub.redis_listener.RedisListenerDetails;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static xyz.ruhshan.redis_pubsub.redis_listener.ListenerScanner.getListeners;

@Component
@Slf4j
public class MasterListener implements MessageListener {
    private final ObjectMapper objectMapper;

    public MasterListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {

        String channel = new String(message.getChannel());

        List<RedisListenerDetails> redisListeners =  getListeners(channel);

        redisListeners.forEach(redisListenerDetails -> {
            parseAndInvokeListener(message, redisListenerDetails, channel);

        });

    }

    private void parseAndInvokeListener(Message message, RedisListenerDetails redisListenerDetails, String channel) {
        try {
            Object deserialized = objectMapper.readValue(message.getBody(), redisListenerDetails.getParameterType());
            redisListenerDetails.getMethod().invoke(redisListenerDetails.getBean(), deserialized);
        }catch (Exception e){
            log.error("Error while invoking listener for channel {}, payload: {}",channel,new String(message.getBody()), e);
        }
    }


}
