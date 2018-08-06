package org.apache.spark.streamingprocessing

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Durations, StreamingContext}

object SparkStreamingDataProcessing {
  def main(args: Array[String]): Unit = {
    /**
      * Path : /Streaming/checkpoint/  .set("spark.driver.host", "localhost")
      */
    val ssc : StreamingContext = StreamingContext.getOrCreate("D:\\checkpoint",functionToCreateContext _)
    ssc.start()
    ssc.awaitTermination()
  }

  def functionToCreateContext(): StreamingContext ={
    val conf = new SparkConf().setMaster("local").setAppName("streaming").set("spark.driver.host", "localhost")
    val ssc = new StreamingContext(conf,Durations.seconds(30))

    val kafkaParams : Map[String,Object] = Map[String,Object](
      "bootstrap.servers" -> "bourne:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "sparkconsumer",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("TOPIC_IN")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    val map = stream.map(line=> (line.key(),line.value()))

    val key = map.flatMap(line=>line._2.split(" ")).map(word => (word,1)).reduceByKey((a,b)=>(a+b))
    key.checkpoint(Durations.seconds(60))

    key.print()

    ssc.checkpoint("D:\\checkpoint\\")
    ssc
  }

}
