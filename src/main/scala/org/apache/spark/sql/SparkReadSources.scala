package org.apache.spark.sql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{Dataset, SparkSession}


case class Person(age: Long, name: String)

object SparkReadSources {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().config("spark.driver.host", "localhost").master("local[8]").getOrCreate()
    readTextFile(spark)
  }

  def readCsvFile(spark: SparkSession): Unit = {
    val csvData = spark.read.csv("");
    csvData.createOrReplaceTempView("A")
  }

  def readTextFile(spark: SparkSession): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val line = spark.read.textFile("D:\\data\\wordcount.txt").rdd.partitions.length
    val lines = spark.read.textFile("D:\\data\\wordcount.txt").rdd
    println(line)
    val mapToPair = lines.flatMap(line => line.split(" ")).map(word => (word, 1))

    val rbk = mapToPair.reduceByKey((a, b) => (a + b))
    rbk.saveAsTextFile("D:\\data\\wc\\rdd")
  }

  def readJson(spark: SparkSession): Unit = {
    import spark.implicits._
    val json: Dataset[Person] = spark.read.json("D:\\data\\people.json").as[Person]
    json.printSchema()
    json.createOrReplaceTempView("A")
    val sql = spark.sql("select name,age FROM A")

    sql.show()

    sql.write.format("json").json("D:\\data\\wc\\json")

  }
}


