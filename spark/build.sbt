name := "WordCount"

version := "0.0.1"

organization := "gailo22"

scalaVersion := "2.12.4"

scalacOptions += "-Ypartial-unification"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0"

//
//resolvers += Resolver.mavenLocal
//
//resolvers += Resolver.typesafeRepo("release")
//resolvers += Resolver.sonatypeRepo("release")
//resolvers += Resolver.sonatypeRepo("public")
//
//test in assembly := {}
//
//assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
