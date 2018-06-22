class simple(name: String, id: Int) {

  println(name, id)
  println("Primaryconstructor")

  def this(id: Int, name: String, x: Int) {
    this(name, id)
    println(id, name, x)
    println("Auxilaryconstructor")
  }
}

object obj {
  def main(args: Array[String]): Unit = {

    val s = new simple("Vinoth", 5)
    val s2 = new simple(5, "Vijay", 5)
  }
}
