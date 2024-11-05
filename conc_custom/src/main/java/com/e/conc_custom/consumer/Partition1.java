package com.e.conc_custom.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;





@Component
public class Partition1 {

    private static final Logger log = LoggerFactory.getLogger(Partition1.class);

    @KafkaListener( groupId = "group1" , topicPartitions = @TopicPartition(topic = "topic-c", partitions = {"1","2"}))
    void listener (String data) {

      log.info("Received message [{}] in group1", data);

     }
    
}