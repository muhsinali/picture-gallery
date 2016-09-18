name := "picture-gallery"

version := "1.0-SNAPSHOT"

autoScalaLibrary := false

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

play.Project.playJavaSettings
