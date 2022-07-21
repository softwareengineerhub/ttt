package com.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer2 {

    // @KafkaListener(topics = "t_topic", groupId = "default-spring-consumer")
    public void onMessage(String message){
        System.out.println(hashCode()+" ;message="+message);
    }
}
