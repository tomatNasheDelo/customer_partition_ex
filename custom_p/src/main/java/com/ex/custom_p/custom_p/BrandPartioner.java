package com.ex.custom_p.custom_p;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;
import org.springframework.stereotype.Component;


@Component
public class BrandPartioner implements Partitioner{

    private String brand;

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster){

       int chosenPartition;

       List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
       int numPartitions = partitions.size();

       if((keyBytes == null) || (!(key instanceof String))){
        throw new InvalidRecordException("All messages should have a valid key ");

       }

       if (((String) key).equalsIgnoreCase(brand)){
          chosenPartition = 0;
       } else {
          chosenPartition = Utils.toPositive(Utils.murmur2(keyBytes)) % (numPartitions - 1) + 1;
       }
         System.out.println("For "+value + " partition chosen: "+ chosenPartition);
         
         return chosenPartition;
       
          
       



    }
        public void close(){

        }

          public void configure(Map<String, ?> map){
            brand = (String) map.get("partition.brand");
          }
    
}
