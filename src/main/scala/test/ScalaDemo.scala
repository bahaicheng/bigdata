package test

<<<<<<< HEAD
import java.util.concurrent.{Callable, Future}

import akka.dispatch.Futures

import scala.actors.threadpool.FutureTask
import scala.concurrent.Await
import scala.util.control.Breaks


case class Ps(name : String , age : String)


object ScalaDemo{

  object sss{
    implicit def strToInt(x : String) =x.toInt
  }

=======
import java.util.Date

import scala.util.control.Breaks

case class Ps(name: String, age: String)

object ScalaDemo {
>>>>>>> c75026a44ba237850e15e396ff3f0af8058498ed
  def main(args: Array[String]): Unit = {

  }

<<<<<<< HEAD
  def implicitDemo(@volatile a : StringablesRecord): Unit ={
    import test.ScalaDemo.sss._
    val a :Int = "1"
  }

  def drop1[A](l: List[A]) = l.tail



  def f(s: String) = "func(" + s + ")"

  def demo1(): Unit ={
    val xx = (x:Int) =>x+1
    println(xx(1))
    val m = func(xx(2),_ : Int)
    m(xx(1))
  }

  def func(a : Int,b : Int): Unit ={
    println(a*2+b)
  }



  def loop(){
    val loop = new Breaks
    loop.breakable(
      while (true){
        loop.break()
      }
    )
    println("=====")
  }

  def classDemo(): Unit ={
    var w :WC = new WC();
=======
  def mapOption(str :Option[String]): String = str match{
    case Some(s) => s
    case None => "none"
  }

  def setTest(): Unit ={
    import scala.collection.mutable.Set//可变set需要引入
    val mutableSet :Set[Int] = Set(1,2,3)
    println(mutableSet.getClass.getName)
    mutableSet += 6
    mutableSet -= 2
    mutableSet.add(8)
    println(mutableSet)
    val an = mutableSet.toSet
    println(an.getClass.getName)
  }

  def listTest(): Unit ={
    var list : List[String] = List("a","b","c")
    println(list)
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
>>>>>>> c75026a44ba237850e15e396ff3f0af8058498ed
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
