addSbtPlugin("com.eed3si9n" % "sbt-doge" % "0.1.5")

lazy val metaroot = (project in file(".")).
  dependsOn(metaSbtSomething)

lazy val metaModel = (project in file("model")).
  settings(
    sbtPlugin := true,
    scalaVersion := "2.10.6",
    unmanagedSourceDirectories in Compile :=
      mirrorScalaSource((baseDirectory in ThisBuild).value.getParentFile / "model")
  )

lazy val metaSbtSomething = (project in file("sbt-plugin")).
  dependsOn(metaModel).
  settings(
    sbtPlugin := true,
    scalaVersion := "2.10.6",
    unmanagedSourceDirectories in Compile :=
      mirrorScalaSource((baseDirectory in ThisBuild).value.getParentFile / "sbt-plugin")
  )

def mirrorScalaSource(baseDirectory: File): Seq[File] = {
  val scalaSourceDir = baseDirectory / "src" / "main" / "scala"
  if (scalaSourceDir.exists) scalaSourceDir :: Nil
  else sys.error(s"Missing source directory: $scalaSourceDir")
}
