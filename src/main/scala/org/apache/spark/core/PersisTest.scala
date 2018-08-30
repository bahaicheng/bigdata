package org.apache.spark.core

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object PersisTest {

  def WordCount(sc : SparkContext): Unit ={
    val start = System.currentTimeMillis()
    val file = sc.textFile("D:\\data\\wordcount.txt")
    //    val rdd1 = file.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _).sortByKey().persist().collect()
    val flatMap = file.flatMap(_.split(" "))
    val map = flatMap.map((_,1))
    //.persist是将程序久化的内存
    val rsp = map.reduceByKey(_ + _).sortByKey()
    val key = rsp.foreach(println(_))
    val end = System.currentTimeMillis()
    println("--------------"+(end-start)+"--------------")
    rsp.foreach(println(_))
    val end1 = System.currentTimeMillis()
    println("--------------"+(end1-end)+"--------------")
    rsp.foreach(println(_))
    val end2 = System.currentTimeMillis()
    println("--------------"+(end2-end1)+"--------------")
  }
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("GroupTopN").set("spark.driver.host", "localhost")
    val sc = new SparkContext(conf)
    var result: String = ""
    WordCount(sc)
    println(result)
    sc.stop
  }
}
