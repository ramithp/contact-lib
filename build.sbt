name := "contact-lib"

organization := "com.rcirka"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4"
)

parallelExecution in Test := false
