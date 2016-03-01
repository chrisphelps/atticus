
scalaVersion := "2.11.7"

//libraryDependencies += "org.typelevel" %% "cats" % "0.4.0"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.10.0",
  "com.github.finagle" %% "finch-circe" % "0.10.0",
  "io.circe" %% "circe-generic" % "0.3.0",
"io.circe" %% "circe-core" % "0.3.0",
"io.circe" %% "circe-parser" % "0.3.0"
)
