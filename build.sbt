name := "BER"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

appDependencies ++= Seq(
  "postgresql" % "postgresql" % "8.4-702.jdbc4"
)     

play.Project.playJavaSettings
