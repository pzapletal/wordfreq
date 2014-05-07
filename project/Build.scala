import sbt._

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
  val onejar        = "commons-lang" % "commons-lang" % "2.6"
}

object Dependencies {

  import Library._

  val wordfreq = List(
    newman,
    onejar,
    scalaTest    % "test"

  )
}
