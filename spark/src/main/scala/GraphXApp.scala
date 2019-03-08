import org.apache.spark.SparkContext
import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.rdd.RDD

object GraphXApp extends App {

  val sc = new SparkContext("local", "GraphX App")

  val vertices: RDD[(Long, String)] = sc.parallelize(Array(
    (1L, "John"),
    (2L, "Maria"),
    (3L, "Patrick"),
    (4L, "Maurice")
  ))

  val edges = sc.parallelize(Array(
    Edge(1L, 2L, "loves"),
    Edge(1L, 3L, "hates"),
    Edge(2L, 3L, "loves"),
    Edge(4L, 2L, "loves")
  ))

  val graph = Graph(vertices, edges)

  graph.subgraph(
    epred = triplet => triplet.attr == "loves")
    .triplets.map { triplet =>
    s"${triplet.srcAttr} ${triplet.attr} ${triplet.dstAttr} "
  }.foreach(println)

  val nbEdges = graph.edges.filter(_.attr == "loves").count()

  println(s"${nbEdges} love")

}
