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

name := "scalajs-snabbdom"
enablePlugins(ScalaJSPlugin)
BintrayRelease.settings
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0"
scalacOptions += "-P:scalajs:sjsDefinedByDefault"
