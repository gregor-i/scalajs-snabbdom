import scala.sys.process._

version in ThisBuild := {
  Option("git tag -l --points-at HEAD".!!.trim)
    .filter(_.nonEmpty)
    .getOrElse("SNAPSHOT")
}
organization in ThisBuild := "com.github.gregor-i"
scalaVersion in ThisBuild := "2.13.1"
crossScalaVersions in ThisBuild := Seq("2.13.1")
crossScalaVersions := Nil

lazy val root = (project in file("."))
  .settings(name := "scalajs-snabbdom")
  .enablePlugins(ScalaJSPlugin)
  .settings(
    BintrayRelease.settings,
    libraryDependencies += "org.scala-js"  %%% "scalajs-dom" % "1.1.0",
    libraryDependencies += "org.scalatest" %%% "scalatest"   % "3.2.3" % Test,
    testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD"),
    scalaJSLinkerConfig in Test ~= { _.withModuleKind(ModuleKind.ESModule) }
  )
