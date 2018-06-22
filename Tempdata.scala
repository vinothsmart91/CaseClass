import org.apache.spark.metrics.source
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf,SparkContext};
import scala.io.Source;

case class TempData(day:Int,doy:Int,month:Int,year:Int,precip:Double,snow:Double,tave:Double,tmax:Double,tmin:Double)

object test extends App{
  //valconf=newSparkConf().setMaster("local[*]").setAppName("Checkingdata")
  //valsc=newSparkContext(conf)
  //valsQLContext=newSQLContext(sc)
  val source=scala.io.Source.fromFile("C:\\Users\\611052474\\Desktop\\MN212142_9392.csv")
  val lines=source.getLines().drop(1)
  val data=lines.flatMap{line=>
    val p=line.split(",")
    if(p(7)=="."||p(8)=="."||p(9)==".")Seq.empty else
    Seq(TempData(p(0).toInt,p(1).toInt,p(2).toInt,p(4).toInt,
      convertodouble(p(5)),convertodouble(p(6)),p(7).toDouble,p(8).toDouble,
      p(9).toDouble))
  }.toArray
  source.close()

  val maxTemp=data.map(_.tmax).max
  val hotDays=data.filter(_.tmax==maxTemp)
  println(s"Hotdaysare${hotDays.mkString(",")}")

  val maximum=data.maxBy(_.tmax)
  val samehotday=data.reduceLeft((d1,d2)=>if(d1.tmax>=d2.tmax)d1 else d2)

  println("Hotday"+maximum)
  println("SameHotday"+samehotday)

  val percip=data.count(_.precip>1.0)

  println(percip)
  val flatmapprec=data.flatMap(x=>if(x.precip>1.0)Seq.empty else Seq(x.tmax))
  val groupedmonth=data.groupBy(_.month)
  val maxmonth=groupedmonth.map{case(m,days)=>m->days.foldLeft(0.0)((sum,td)=>sum+td.tmax)/days.length}
  println("Maxmonth"+maxmonth)
  val sortedmaxmonth=maxmonth.toSeq.sortBy(_._1)
  sortedmaxmonth.foreach(println)
  def convertodouble(s:String):Double={
    try{
      s.toDouble
    }
    catch{
      case _:NumberFormatException => -1
    }
  }
}

