name := "spray-servlet"

version := "1.0"

scalaVersion := "2.11.7"


libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.12"

val sprayVersion = "1.3.3"

libraryDependencies += "io.spray" %% "spray-routing" % sprayVersion

libraryDependencies += "io.spray" %% "spray-servlet" % sprayVersion

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"

enablePlugins(TomcatPlugin)

enablePlugins(WarPlugin)
