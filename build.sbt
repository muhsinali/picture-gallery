name := "picture-gallery"

version := "1.0-SNAPSHOT"

autoScalaLibrary := false

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

libraryDependencies += "org.mongodb" % "mongodb-driver" % "3.2.0"

libraryDependencies += "org.mongodb.morphia" % "morphia" % "1.2.1"

play.Project.playJavaSettings
