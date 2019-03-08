import org.apache.spark.SparkContext
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

object KMeansApp extends App {

  val sc = new SparkContext("local", "KMeans App")

  val path = "data/input/iris.data"
  val data = sc.textFile(path)
    .map(line =>
    Vectors.dense(
      line.split(",").slice(0, 3).map(_.toDouble))
    )
  val computedCenters: KMeansModel = KMeans.train(data, k = 3, maxIterations = 20)
  val WSSE = computedCenters.computeCost(data)

  println(s"Within Set Sum Error: $WSSE")

}
