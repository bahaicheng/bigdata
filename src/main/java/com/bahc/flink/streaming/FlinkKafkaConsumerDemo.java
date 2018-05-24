package com.bahc.flink.streaming;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.KeyedDeserializationSchema;

import java.util.List;
import java.util.Properties;

public class FlinkKafkaConsumerDemo<T> extends FlinkKafkaConsumer010<T> {
    public FlinkKafkaConsumerDemo(String topic, DeserializationSchema<T> valueDeserializer, Properties props) {
        super(topic, valueDeserializer, props);
    }

    public FlinkKafkaConsumerDemo(List<String> topics, KeyedDeserializationSchema<T> deserializer, Properties props) {
        super(topics, deserializer, props);
    }

    public FlinkKafkaConsumerDemo(List<String> topics, DeserializationSchema<T> deserializer, Properties props) {
        super(topics, deserializer, props);
    }

    public FlinkKafkaConsumerDemo(String topic, KeyedDeserializationSchema<T> deserializer, Properties props) {
        super(topic, deserializer, props);
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    public void open(Configuration configuration) {
        super.open(configuration);
    }
}
