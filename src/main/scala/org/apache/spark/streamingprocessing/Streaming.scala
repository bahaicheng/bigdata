package org.apache.spark.streamingprocessing

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Milliseconds, StreamingContext}

object Streaming {
  def main(args: Array[String]): Unit = {
      val kafkaParams : Map[String,Object] = Map[String,Object](
        "bootstrap.servers" -> "bourne.9092",
        "keydesrializer" -> classOf[StringDeserializer],
        "value.deserializer" -> classOf[StringDeserializer],
        "group.id" -> "sparkstreaming",
        "auto.offset.reset" -> "latest",
        "enable.auto.commit" -> (false: java.lang.Boolean))

    val conf = new SparkConf().setAppName("streaming").setMaster("local[3]")
    val ssc = new StreamingContext(conf,Milliseconds(100))

    val topics = Array("topicA", "topicB")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

  }
}
