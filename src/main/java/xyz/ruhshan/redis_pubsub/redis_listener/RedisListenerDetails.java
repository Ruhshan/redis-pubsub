package xyz.ruhshan.redis_pubsub.redis_listener;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@AllArgsConstructor
@Getter
public class RedisListenerDetails {
    private Object bean;
    private Method method;
    private Class<?> parameterType;


}
