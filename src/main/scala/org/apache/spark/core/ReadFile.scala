package org.apache.spark.core

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object ReadFile {
  def main(args: Array[String]): Unit = {
    spark1
  }

  def spark(): Unit ={
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).master("local").getOrCreate()
    val file = spark.read.textFile("D:\\data\\Hello1.txt")
    file.show()
    file.foreach{line=> println(line)}
  }

  def spark1(): Unit ={

    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).master("local").getOrCreate()
    val csv = spark.read.csv("D:\\data\\2000_cols_data.csv")
    csv.printSchema()
    csv.show()
  }

}
