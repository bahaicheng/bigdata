package com.bahc.kafka.producer;

public class KafkaProperties {
    public static final String TOPIC = "TOPIC_IN";
    public static final String KAFKA_SERVER_URL = "192.168.121.66";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final String TOPIC2 = "kafkatopic";
    public static final String TOPIC3 = "kafkatopic";
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";

    private KafkaProperties() {}
}
