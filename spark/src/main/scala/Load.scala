import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object Load extends App  {

  val sc = new SparkContext("local", "App")

  val sqlContext = new SQLContext(sc)

  val df = sqlContext.load("authors.parquet")

  df.show()
  df.printSchema()

  df.write.json("authors.json")

}
