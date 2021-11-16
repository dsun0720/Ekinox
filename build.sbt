name := "Ekinox"

version := "0.1"

scalaVersion := "2.12.15"

organization := "com.examples"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test

Test / parallelExecution := false

assembly / test := {}

assembly / mainClass  := Some("com.examples.DeadOrAliveGameInteractive")
assembly / assemblyJarName := "Ekinox.jar"
