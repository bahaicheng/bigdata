package com.bahc.flink.streaming;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public class FlinkStreamingPorcessJava {

    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.enableCheckpointing(1000, CheckpointingMode.EXACTLY_ONCE);
        env.setStateBackend(new FsStateBackend("hdfs://localhost:9000/flink/checkpoints"));
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);//与enableCheckpointing中的EXACTLY_ONCE设置一个就可以

        Properties consumerProps = new Properties();
        consumerProps.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.121.66:9092");
        consumerProps.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "flinkconsumer2");

        String topicIn = "TOPIC_IN";
        String topicOut = "TOPIC_OUT";

        DataStream<String> stream = env.addSource(new FlinkKafkaConsumer010<String>(topicIn, new SimpleStringSchema(), consumerProps));
        DataStream<String> flatmap = stream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String line, Collector<String> collector) throws Exception {
                for (String arr : line.split("\\W+")) {
                    collector.collect(arr);
                }
            }
        });

        DataStream<Tuple2<String, Integer>> map = flatmap.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        DataStream<Tuple2<String, Integer>> sum = map.keyBy(0).window(TumblingEventTimeWindows.of(Time.seconds(10))).sum(1);

        sum.print();

        DataStream<String> map1 = sum.map(new MapFunction<Tuple2<String, Integer>, String>() {
            @Override
            public String map(Tuple2<String, Integer> tuple) throws Exception {
                return tuple.f0 + "," + tuple.f1.toString();
            }
        });

        Properties producerProps = new Properties();
        producerProps.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.121.66:9092");

        FlinkKafkaProducer010<String> myProducer = new FlinkKafkaProducer010<String>(topicOut,new SimpleStringSchema(),producerProps);
        map1.addSink(myProducer);

        env.execute();
    }

}
