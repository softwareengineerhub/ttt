package com.app.consumer;

import com.google.common.primitives.Longs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.kafka.support.KafkaHeaders.OFFSET;
import static org.springframework.kafka.support.KafkaHeaders.PARTITION_ID;

@Component
public class MyConsumer {

    //@KafkaListener(topics = "t_topic", groupId = "default-spring-consumer")
    //@KafkaListener(topics = "t_topic", groupId = "default-spring-consumer")
    public void onMessage(String message, @Header(OFFSET) long offset, KafkaConsumer kafkaConsumer, ConsumerRecord record, @Header(value = "currentTime", required = false) byte[] currentTimestamp) {
        if (currentTimestamp != null) {
            long cts = Longs.fromByteArray(currentTimestamp);
            System.out.println("cts=" + cts);
        }

        System.out.println(hashCode() + " ;message=" + message + "; OFFSET=" + offset + "; consumer=" + kafkaConsumer);

        System.out.println("subscriptions="+kafkaConsumer.subscription());

        List<PartitionInfo> partitionInfoList = kafkaConsumer.partitionsFor("t_topic");
        /*for(PartitionInfo partitionInfo: partitionInfoList){
            int partitionId = partitionInfo.partition();
            TopicPartition topicPartition = new TopicPartition("t_topic", partitionId);
            List<TopicPartition> topicPartitions = Arrays.asList(topicPartition);
            Map<TopicPartition, Long> partitionMapEndOffset = kafkaConsumer.endOffsets(topicPartitions);
            System.out.println("partitionMapEndOffset="+partitionMapEndOffset);
        }*/

        /*for(PartitionInfo partitionInfo: partitionInfoList){
            int partitionId = partitionInfo.partition();
            TopicPartition topicPartition = new TopicPartition("t_topic", partitionId);
            List<TopicPartition> topicPartitions = Arrays.asList(topicPartition);
            Map<TopicPartition, Long> partitionMapEndOffset = kafkaConsumer.endOffsets(topicPartitions);
            Long endOffset = partitionMapEndOffset.get(topicPartition);
            System.out.println("-------------description------------------");
            System.out.println("endOffset="+endOffset);
            System.out.println("currentOffset="+offset);
            System.out.println("lag="+(endOffset-offset));
        }*/

        int partitionId = record.partition();
        System.out.println("partitionId=" + partitionId);
        TopicPartition topicPartition = new TopicPartition("t_topic", partitionId);
        List<TopicPartition> topicPartitions = Arrays.asList(topicPartition);
        Map<TopicPartition, Long> partitionMapEndOffset = kafkaConsumer.endOffsets(topicPartitions);
        Long endOffset = partitionMapEndOffset.get(topicPartition);
        System.out.println("-------------description------------------");
        System.out.println("endOffset=" + endOffset);
        System.out.println("currentOffset=" + offset);
        System.out.println("lag=" + (endOffset - offset));
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
