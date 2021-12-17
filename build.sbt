import scala.sys.process._

ThisBuild / version := {
  Option("git tag -l --points-at HEAD".!!.trim)
    .filter(_.nonEmpty)
    .getOrElse("SNAPSHOT")
}
ThisBuild / organization := "com.github.gregor-i"
ThisBuild / scalaVersion := "2.13.7"
ThisBuild / crossScalaVersions := Seq("2.13.7")
crossScalaVersions := Nil

lazy val snabbdom = (project in file("snabbdom"))
  .settings(name := "scalajs-snabbdom")
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.0.0",
    scalaTest,
    Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }
  )

lazy val toasts = (project in file("toasts"))
  .settings(name := "snabbdom-toasts")
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(snabbdom % "compile->compile;test->test")
  .settings(
    scalaTest,
    Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }
  )

lazy val components = (project in file("components"))
  .settings(name := "snabbdom-components")
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(snabbdom % "compile->compile;test->test")
  .settings(
    scalaTest,
    Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }
  )

def scalaTest = Seq(
  libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.10" % Test,
  testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
)
