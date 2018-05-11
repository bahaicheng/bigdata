import org.apache.WC

import scala.util.control.Breaks

case class Ps(name : String , age : String)
object ScalaDemo {
  def main(args: Array[String]): Unit = {
//    prinltDemo()
    classDemo()

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
