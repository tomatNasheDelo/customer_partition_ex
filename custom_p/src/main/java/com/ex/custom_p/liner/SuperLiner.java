package com.ex.custom_p.liner;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ex.custom_p.custom_p.BrandPartioner;



@Component
public class SuperLiner implements CommandLineRunner {


    public void run(String ... args) throws Exception {
        Properties kafkaProps = new Properties();

        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, BrandPartioner.class);
        kafkaProps.put("partition.brand", "apple");

        Producer<String, String> producer = new KafkaProducer<>(kafkaProps);

               try{
                  for (int i = 0; i <= 20; i++){
                    if (i < 3){
                    ProducerRecord<String, String> apple = 
                           new ProducerRecord<String, String>("topic-c", "apple", "Selling Apple Device");
                           producer.send(apple, new DemoProducerCallback());
                  } else {
                          ProducerRecord<String, String> samsung = 
                                new ProducerRecord<String, String> ("topic-c", "others_"+i, "Selling other Device");
                            producer.send(samsung, new DemoProducerCallback ());
                  }
               }
            } catch (Exception e) {
                e.printStackTrace();
                     
            }

        
    }
    
}
  class DemoProducerCallback implements Callback{
      public void onCompletion(RecordMetadata recordMetadata, Exception e){
           if(e != null){
               e.printStackTrace();
           }
      }
  }
