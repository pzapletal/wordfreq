import com.github.retronym.SbtOneJar._

name := "wordfreq"

version := "1.0"

autoCompilerPlugins := true

scalaVersion := Version.scala

oneJarSettings

libraryDependencies ++= Dependencies.wordfreq

scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)




