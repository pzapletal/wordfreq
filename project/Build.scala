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
}

object Dependencies {

  import Library._

  val wordfreq = List(
    newman,
    scalaTest    % "test"

  )
}
