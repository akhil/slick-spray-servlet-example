name := "slick-spray-servlet-example"

version := "1.0"

scalaVersion := "2.11.7"

val sprayVersion = "1.3.3"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9",
  "com.typesafe.akka" %% "akka-actor" % "2.4.0",
  "io.spray" %% "spray-can" % sprayVersion,
  "io.spray" %% "spray-httpx" % sprayVersion,
  "io.spray" %% "spray-util" % sprayVersion,
  "io.spray" %% "spray-routing" % sprayVersion,
  "io.spray" %% "spray-servlet" % sprayVersion,
  "io.spray" %% "spray-caching" % sprayVersion,
  "com.typesafe.slick" %% "slick" % "3.1.0",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.h2database" % "h2" % "1.4.190",
  "io.spray" %% "spray-testkit" % sprayVersion % Test
)

enablePlugins(TomcatPlugin)

enablePlugins(WarPlugin)
