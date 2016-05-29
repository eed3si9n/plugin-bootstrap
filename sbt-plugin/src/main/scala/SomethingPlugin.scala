package foo

import sbt._

object SomethingPlugin extends AutoPlugin {
  def requries = sbt.plugins.JvmPlugin
  object autoImport {
    lazy val something = taskKey[Unit]("")
    lazy val somethingX = settingKey[String]("")
  }
  import autoImport._
  override def projectSettings = Seq(
    something := { println(s"something! ${Model.x}") }
  )
}
