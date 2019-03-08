import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object StreamingApp extends App {

  def compute(input: RDD[String]) = input
    .flatMap(_.split(" "))
    .map((_, 1))
    .reduceByKey(_ + _)

  val sc = new SparkContext("local[2]", "Streaming App")
  import org.apache.spark.streaming._
  val ssc = new StreamingContext(sc, Seconds(2))

  val stream = ssc.socketTextStream("localhost", 9999)
  stream
    //    .map(sentence => (sentence, sentence.length))
//    .flatMap(_.split(" "))
//    .map((_, 1))
//    .reduceByKeyAndWindow((a: Int, b: Int) => a + b, Seconds(30), Seconds(10))
      .window(Seconds(30), Seconds(10))
      .transform(rdd => compute(rdd))
      .print()

  ssc.start()
  ssc.awaitTermination()
}
