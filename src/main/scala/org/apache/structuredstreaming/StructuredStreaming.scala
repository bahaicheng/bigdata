package org.apache.structuredstreaming

import org.apache.spark.sql.streaming.DataStreamWriter
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object StructuredStreaming {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").config("spark.driver.host", "localhost").getOrCreate()
    val sc = spark.sparkContext
    import spark.implicits._
    sc.setCheckpointDir("")
    val lines: Dataset[String] = spark.readStream.textFile("D:\\data\\wc")
    val words: Dataset[String] = lines.flatMap(_.split(" "))
    val wordCounts: Dataset[Row] = words.groupBy("value").count()

    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()

  }
}
