name := "fileUpload"

version := "1.0-SNAPSHOT"

autoScalaLibrary := false

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

play.Project.playJavaSettings
