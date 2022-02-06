import Dependencies._

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "monad-transformers-exercises",
    scalacOptions ++= Seq("-Ymacro-annotations", "-feature"),
    libraryDependencies += scalaTest % Test,
    libraryDependencies ++= Seq(
      "io.estatico" %% "newtype" % "0.4.4",
      "io.circe" %% "circe-core" % "0.14.1",
      "io.circe" %% "circe-literal" % "0.14.1",
      "io.circe" %% "circe-parser" % "0.14.1",
      "io.circe" %% "circe-generic" % "0.14.1",
      "io.circe" %% "circe-generic-extras" % "0.14.1",
      "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
      "joda-time" % "joda-time" % "2.10.13"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
