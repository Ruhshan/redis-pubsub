package xyz.ruhshan.redis_pubsub.redis_listener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

@Component
public class ListenerScanner {

    private static final Map<String, List<RedisListenerDetails>> redisListenerMap = new HashMap<>();

    public ListenerScanner(ApplicationContext applicationContext) {
        scanListeners(applicationContext);
    }

    public static List<String> getTopics() {
        return new ArrayList<>(redisListenerMap.keySet());
    }

    public static List<RedisListenerDetails> getListeners(String topic) {
        return redisListenerMap.get(topic);
    }

    private void scanListeners(ApplicationContext applicationContext) {

        List<String> beanNames = Stream.of(applicationContext.getBeanDefinitionNames())
                .filter(beanName -> !beanName.contains("listenerScanner"))
                .toList();

        beanNames.forEach(beanName -> processBean(applicationContext, beanName));
    }

    private void processBean(ApplicationContext applicationContext, String beanName) {

        Object bean = applicationContext.getBean(beanName);
        Method[] methods = bean.getClass().getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(RedisListener.class)) {

                RedisListenerDetails listenerDetails =
                        new RedisListenerDetails(bean, method, method.getParameterTypes()[0]);


                RedisListener annotation = method.getDeclaredAnnotation(RedisListener.class);


                String topic = annotation.topic();

                redisListenerMap
                        .computeIfAbsent(topic, k -> new ArrayList<>())
                        .add(listenerDetails);
            }
        }
    }
}
