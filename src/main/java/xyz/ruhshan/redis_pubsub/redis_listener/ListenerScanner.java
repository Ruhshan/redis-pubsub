package xyz.ruhshan.redis_pubsub.redis_listener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import xyz.ruhshan.redis_pubsub.redis_listener.RedisListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ListenerScanner {

    private static final Map<String, List<RedisListenerDetails>> redisListenerMap = new HashMap<>();
    public ListenerScanner(ApplicationContext applicationContext) {
        scanListeners(applicationContext);
    }

    private void scanListeners(ApplicationContext applicationContext) {


        String[] beanNames = applicationContext.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            if(beanName.contains("listenerScanner")){
                continue;
            }
            Object bean = applicationContext.getBean(beanName);

            // Get all methods of the bean
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                // Check if the method has the specified annotation
                if (method.isAnnotationPresent(RedisListener.class)) {
                    RedisListener annotation = method.getDeclaredAnnotation(RedisListener.class);

                    RedisListenerDetails listenerDetails = new RedisListenerDetails(bean, method, method.getParameterTypes()[0]);

                    redisListenerMap.computeIfAbsent(annotation.topic(), k -> new ArrayList<>()).add(listenerDetails);

                }
            }


        }

    }

    public static List<String> getTopics(){
        return new ArrayList<>(redisListenerMap.keySet());
    }

    public static List<RedisListenerDetails> getListeners(String topic){
        return redisListenerMap.get(topic);
    }
}
