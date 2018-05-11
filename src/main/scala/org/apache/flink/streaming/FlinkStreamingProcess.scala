package org.apache.flink.streaming

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.api.scala._


object FlinkStreamingProcess {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    socketTextStreamDemo(env)

  }

  def socketTextStreamDemo(env: StreamExecutionEnvironment): Unit = {
    val text = env.socketTextStream("192.168.121.66", 9000)
    val counts = text.flatMap {_.toLowerCase.split("\\W+") filter {_.nonEmpty}}
      .map {(_, 1)}
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)
    counts.print()

    env.execute("Window Stream WordCount")
  }
}


