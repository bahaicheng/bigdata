package org.apache.core

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkDemo {

  case class Info(HOL_YEAR: String, HOL_MONTH: String, HOL_DAY: String)

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("spark.driver.host", "localhost")
    val spark = SparkSession.builder().config(conf).master("local").appName("spark").getOrCreate();
    //    val option = spark.read.format("jdbc").option("url", "jdbc:mysql://bourne:3306/holiday").option("dbtable","HOLIDAY_TBL").option("user","root").option("password","root123").load()
    //    option.createOrReplaceTempView("A")

    val dbTable = "HOLIDAY_TBL"
    val jdbc = "jdbc:mysql://bourne:3306/holiday"
    val properties = new Properties()
    properties.put("user", "root")
    properties.put("password", "root123")

    import spark.implicits._
    val tblDF = spark.read.jdbc(jdbc, dbTable, properties)

    tblDF.createOrReplaceTempView("A")

    val sql = spark.sql("select HOL_YEAR,HOL_MONTH,HOL_DAY from A")

    val value = sql.as[Info]

    val filter = value.filter(v1 => v1.HOL_MONTH.equals("05"))

    val vacation = filter.map { info =>
      parser(info)
    }

    vacation.printSchema()
    vacation.show()

    println("====="+spark.version)

  }

  def parser(info: Info): String = {
    info.HOL_YEAR + info.HOL_MONTH + info.HOL_DAY
  }

}
