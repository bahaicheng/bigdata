package test

class Student (var name:String,var address:String){

  private var phone="110"
  //直接访问伴生对象的私有成员
  def infoCompObj() = println("伴生类中访问伴生对象：" + Student.sno)
}

object Student {

  private var sno:Int = 100
  def incrementSno()={

    sno += 1 //加1
    sno  //返回sno
  }

  //定义apply方法,实例化伴生类
  def apply(name1:String,address1:String)= new Student(name1,address1)


  def main(args: Array[String]): Unit = {

    println("单例对象：" + Student.incrementSno()) //单例对象

    //实例化伴生类
    val obj = new Student("yy","bj")
    obj.infoCompObj();

    println("通过伴生对象的apply方法访问伴生类成员:")
    val obj2 = Student("yy_apply","bj_apply") //实际是通过apply方法进行了对象实例化，避免了手动new对象
    println(obj2.name)
    println(obj2.address)
  }
}


