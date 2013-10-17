organization := "net.vidageek"

name         := "ong"

version in Global := "1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq("junit" % "junit" % "4.10", 
							"org.specs2" % "specs2_2.10" % "2.2",
							"br.com.caelum" % "vraptor" % "3.5.3"             
							)

EclipseKeys.withSource := true