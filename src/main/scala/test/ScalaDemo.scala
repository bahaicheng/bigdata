package test

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

  def main(args: Array[String]): Unit = {

  }

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
    w.word = "scala"
    w.count = 1
    println(w)
  }

  def prinltDemo(): Unit ={
    val arr = Array("my","name","is")
    printChar(arr:_*)
    printChar("math","compute","dell")
  }

  def printChar(arr :String*): Unit ={
      println(arr)
    arr.foreach(x => println(x))
  }

}
