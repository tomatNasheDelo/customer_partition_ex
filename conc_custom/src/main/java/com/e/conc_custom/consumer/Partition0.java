package com.e.conc_custom.consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;


//@Component
public class Partition0 {

    private static final Logger log = LoggerFactory.getLogger(Partition0.class);

    @KafkaListener( groupId = "group1" , topicPartitions = @TopicPartition(topic = "topic-c", partitions = {"0"}))
    void listener (String data) {

      log.info("Received message [{}] in group1", data);

     }
    
}
