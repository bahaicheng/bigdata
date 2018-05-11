package com.bahc.flink.streaming.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public class FlinkStreamingPorcessWindowsFunction {
    private static final String topicIn = "TOPIC_IN";

    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        Properties consumerProps = new Properties();
        consumerProps.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.121.66:9092");
        consumerProps.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "flinkconsumer2");

        DataStream<String> stream = env.addSource(new FlinkKafkaConsumer010<>(topicIn, new SimpleStringSchema(), consumerProps));

        DataStream<String> flatmap = stream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String s, Collector<String> collector) throws Exception {
                String[] split = s.split("\\W+");
                for (String str : split) {
                    collector.collect(str);
                }
            }
        });

        DataStream<Tuple2<String, Integer>> map = flatmap.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}