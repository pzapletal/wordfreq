import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object Version {
  val scala     = "2.10.3"
  val newman    = "1.3.5"
  val logback   = "1.0.13"
  val scalaTest = "2.0"
}

object Library {
  val newman         = "com.stackmob"      %% "newman" % "1.3.5"
  val logbackClassic = "ch.qos.logback"    %  "logback-classic" % Version.logback
  val scalaTest      = "org.scalatest"     %% "scalatest"       % Version.scalaTest
}

object Dependencies {

  import Library._

  val wordfreq = List(
    newman,
    scalaTest    % "test"

  )
}

object Builds extends Build {
  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    version := "0.1-SNAPSHOT",
    organization := "com.example",
    scalaVersion := Version.scala
  )

  lazy val app = Project("wordfreq", file("wordfreq"),
    settings = buildSettings ++ assemblySettings) settings(
      // your settings here
    )
}
