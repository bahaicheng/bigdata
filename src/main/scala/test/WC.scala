package test

class WC (var word: String, var count: Int){
  def this() { this("", 0) }

  override def toString: String = word+":"+count

}
