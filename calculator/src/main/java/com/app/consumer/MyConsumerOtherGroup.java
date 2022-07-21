package com.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumerOtherGroup {

    @KafkaListener(topics = "t_topic", groupId = "otherGroup")
    public void onMessage(String message){
        System.out.println(hashCode()+" ;message="+message);
    }
}
