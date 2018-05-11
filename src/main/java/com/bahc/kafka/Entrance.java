package com.bahc.kafka;

import com.bahc.kafka.consumer.Consumer;
import com.bahc.kafka.producer.KafkaProperties;
import com.bahc.kafka.producer.Producer;

public class Entrance {
    public static void main(String[] args) {
        Producer producer = new Producer(KafkaProperties.TOPIC,false);
        producer.start();

//        Consumer consumerThread = new Consumer(KafkaProperties.TOPIC);
//        consumerThread.start();
    }
}
