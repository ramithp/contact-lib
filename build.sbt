name := "contact-lib"

organization := "com.contact"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4",
  "com.typesafe.akka" %% "akka-actor" % "2.4.2",
  "me.lessis" %% "courier" % "0.1.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.2"
)

parallelExecution in Test := false
