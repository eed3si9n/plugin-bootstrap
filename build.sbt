import Dependencies._

lazy val root = (project in file(".")).
  aggregate(model, framework, sbtSomething).
  settings(inThisBuild(List(
      scalaVersion := scala210,
      organization := "com.example"
    )),
    name := "Something Root"
  )

// Defines common models for both sbt-plugin and framework
lazy val model = (project in file("model")).
  settings(
    name := "Something Model",
    crossScalaVersions := Seq(scala211, scala210)
  )

// The framework. Ideally, the sbt plugin is run as part of building this.
lazy val framework = (project in file("framework")).
  enablePlugins(SomethingPlugin).
  dependsOn(model).
  settings(
    name := "Something Framework",
    crossScalaVersions := Seq(scala211, scala210),
    // using sbt-something
    somethingX := "a"
  )

lazy val sbtSomething = (project in file("sbt-plugin")).
  dependsOn(model).
  settings(
    sbtPlugin := true,
    name := "sbt-something",
    crossScalaVersions := Seq(scala210)
  )
