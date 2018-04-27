import scala.util.control.Breaks

case class Ps(name : String , age : String)
object ScalaDemo {
  def main(args: Array[String]): Unit = {
//    prinltDemo()
    println(1 to 10)

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
