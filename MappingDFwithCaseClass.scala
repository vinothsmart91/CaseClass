import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext};

case class employee(name: String, age: Int, salary: Int)

object SimpleScalaCase extends App {

  val conf = new SparkConf().setMaster("local[*]").setAppName("Checkingdata")
  val sc = new SparkContext(conf)
  val sQLContext = new SQLContext(sc)
  val data = sc.parallelize(Seq(("vinoth", 26, 45000), ("Vidhya", 25, 15000), ("Dinesh", 26, 80000)), 4)
  val mappedRDD = data.map(x => employee(x._1, x._2, x._3))
  val df = sQLContext.createDataFrame(mappedRDD)
  mappedRDD.foreach(println);
  val groupedbykey = mappedRDD.map(x => (x.age, x)).groupByKey().mapValues(x => x.maxBy(_.salary)).map(x => Seq(x._2.name, x._2.name, x._2.salary))
  df.show()
  groupedbykey.saveAsTextFile("C:\\Users\\611052474\\Desktop\\output")
}
