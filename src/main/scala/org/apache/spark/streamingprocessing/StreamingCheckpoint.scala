package org.apache.spark.streamingprocessing

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StateSpec, StreamingContext}

object StreamingCheckpoint {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("streamingcheckpoint").setMaster("local").set("spark.driver.host", "localhost")
    val sc = new SparkContext(conf);

    val checkpointDir = "_checkpoint"

    def createSC: StreamingContext = {
      val ssc = new StreamingContext(sc, batchDuration = Seconds(5))

      // Create constant input dstream with the RDD
      val rdd = sc.parallelize(0 to 9)
      import org.apache.spark.streaming.dstream.ConstantInputDStream
      val cis = new ConstantInputDStream(ssc, rdd)
      // Sample stream computation

      cis.print

      ssc.checkpoint(checkpointDir)

      ssc
    }

    val ssc = StreamingContext.getOrCreate(checkpointDir, createSC _)
    ssc.start()
    ssc.awaitTermination()
  }

}
