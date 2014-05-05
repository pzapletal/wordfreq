name := "wordfreq"

version := "0.1"

scalaVersion := "2.10.3"

autoCompilerPlugins := true

scalaVersion := Version.scala

//resolvers += "spray-releases" at "http://repo.spray.io"

libraryDependencies ++= Dependencies.wordfreq

scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)
