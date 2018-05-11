package org.apache.flink.batch

import org.apache.flink.api.scala._
import org.apache.flink.api.scala.ExecutionEnvironment

object FlinkBatchPorcess {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    readTextFileData(env)

  }



  def readTextFileData(env : ExecutionEnvironment): Unit ={
    val text : DataSet[String] = env.readTextFile("D:\\data\\wc.txt")
    val counts : DataSet[Tuple2[String,Int]] =text.flatMap{ _.toLowerCase.split("\\W+") filter(_.nonEmpty)}
      .map{(_,1)}
      .groupBy(0)
      .sum(1)
    counts.writeAsCsv("D:\\data\\flink")
    env.execute("Scala WordCount Example")
  }
}
