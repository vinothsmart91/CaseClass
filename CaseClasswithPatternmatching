
abstract class ITcompanies
case class BT(name: String,id: Int) extends ITcompanies
case class Harman(name: String,empid: Int) extends ITcompanies

case object obj extends App
{
  def checkemployeename(company: ITcompanies) : String =
  {
    company match
    {
      case BT("Hello",_) =>"This is Hello employee"
      case Harman("Vinoth",18047) => "Yes the employee present"
    }
  }
  var obj : ITcompanies= BT("Hello",30)
  var result= checkemployeename(obj)
  println(result)
  obj= Harman("Vinoth",18047)
  result= checkemployeename(obj)
  println(result)


}
