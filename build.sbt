name := "picture-gallery"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

autoScalaLibrary := false

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

libraryDependencies += "org.mongodb" % "mongodb-driver" % "3.2.0"

libraryDependencies += "org.mongodb.morphia" % "morphia" % "1.2.1"

libraryDependencies += "org.webjars" % "bootstrap" % "3.3.7"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
