case class simple(name:String,id:Int){

  println(name,id)
  println("Primaryconstructor")

  def this(id:Int,name:String,x:Int){
    this(name,id)
    println(id,name,x)
  }
}

object simple{
  def apply()=new simple("dummy",3)

  def apply(x:Double)=new simple("Vijay",5){
    println(x+5.0)
  }
}

object obj{
  def main(args:Array[String]):Unit={
    //defapply=newsimple("Vijay",10)
    val s=simple("Vinoth",5)
    val s2=new simple(5,"Vijay",5)
    val s3=simple
    val s4=simple(4.0)
    println(s4.id,s4.name)

  }
}
