name := "BER"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "postgresql" % "postgresql" % "8.4-702.jdbc4",
  "mysql" % "mysql-connector-java" % "5.1.18"
)     

play.Project.playJavaSettings
