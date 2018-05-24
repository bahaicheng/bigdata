package test

import java.util.Date

import scala.util.control.Breaks

case class Ps(name: String, age: String)

object ScalaDemo {
  def main(args: Array[String]): Unit = {
    println( apply( layout, 10) )
  }

  def apply(f :Int => String, v: Int) = f(v)

  def layout[A](x: A) = "[" + x.toString() + "]"

  def log(date: Date, message: String) {
    println(date + "===" + message)
  }


  def time(): Long = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }

  def delayed(t: => Long) = {
    println("args:" + t)
  }

  def delayed1(t: Long) {
    println("delayed1:" + t)
  }

  def valDemo(): Unit = {

    val loop = new Breaks
    var a = 0
    loop.breakable(
      while (true) {
        a = a + 1
        if (a == 3) {
          println("break:" + a)
          loop.break()
        }
      }
    )

  }

  def classDemo(): Unit = {
    var w: WC = new WC();
    w.word = "scala"
    w.count = 1
    println(w)
  }

  def prinltDemo(): Unit = {
    val arr = Array("my", "name", "is")
    printChar(arr: _*)
    printChar("math", "compute", "dell")
  }

  def printChar(arr: String*): Unit = {
    println(arr)
    arr.foreach(x => println(x))
  }

}
