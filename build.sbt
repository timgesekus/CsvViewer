name := "CsvViewer"

version := "1.0"

organization := "net.gesekus"

scalaVersion := "2.9.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.github.scala-incubator.io" %% "scala-io-core" % "0.3.0"

libraryDependencies += "com.github.scala-incubator.io" %% "scala-io-file" % "0.3.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1"

libraryDependencies += "junit" % "junit" % "4.8.1"
 
libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0"