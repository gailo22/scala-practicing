import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

case class Author(name: String, nbBooks: Int)

object SQLMain extends App {

  val sc = new SparkContext("local", "App")

  val sqlContext = new SQLContext(sc)

//  val rdd: RDD[Author] = sc.parallelize(List(Author("Kibling", 20), Author("Me", 1)))
//
//  import sqlContext.implicits._
//  val df = rdd.toDF("name", "count")
//  df.show()
//  df.printSchema()

//  val df = sqlContext.createDataFrame(rdd, classOf[Author])
//  df.show()
//  df.printSchema()

  val rdd: RDD[Row] = sc.parallelize(List(("Kibling", 20), ("Me", 1)))
    .map { case (name, count) => Row(name, count)}

  val schema = StructType(List(
    StructField("name", StringType, nullable = false),
    StructField("value", IntegerType, nullable = false),
  ))

  val df = sqlContext.createDataFrame(rdd, schema)
  df.printSchema()
  df.registerTempTable("authors")

  val authorsDF = sqlContext.sql(
    """
      |SELECT * FROM authors WHERE value > 2
    """.stripMargin
  )

  authorsDF.write.save("authors.parquet")

}
