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

lazy val snabbdom = (project in file("snabbdom"))
  .settings(name := "scalajs-snabbdom")
  .enablePlugins(ScalaJSPlugin)
  .settings(
    BintrayRelease.settings,
    libraryDependencies += "org.scala-js"  %%% "scalajs-dom" % "1.1.0",
    scalaTest,
    scalaJSLinkerConfig in Test ~= { _.withModuleKind(ModuleKind.ESModule) }
  )

lazy val toasts = (project in file("toasts"))
  .settings(name := "snabbdom-toasts")
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(snabbdom % "compile->compile;test->test")
  .settings(
    BintrayRelease.settings,
    scalaTest,
    scalaJSLinkerConfig in Test ~= { _.withModuleKind(ModuleKind.ESModule) }
  )

lazy val components = (project in file("components"))
  .settings(name := "snabbdom-components")
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(snabbdom % "compile->compile;test->test")
  .settings(
    BintrayRelease.settings,
    scalaTest,
    scalaJSLinkerConfig in Test ~= { _.withModuleKind(ModuleKind.ESModule) }
  )

def scalaTest = Seq(
  libraryDependencies += "org.scalatest" %%% "scalatest"   % "3.2.3" % Test,
  testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD"),
)
