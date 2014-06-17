

resolvers += "asdf" at "http://jasperreports.sourceforge.net/maven2"

resolvers += Resolver.sonatypeRepo("snapshots")

organization := "net.vidageek"

name         := "ong"

version in Global := "1.0"

scalaVersion := "2.10.3"


libraryDependencies ++= Seq("junit" % "junit" % "4.10", 
							"br.com.caelum" % "vraptor" % "3.5.3",
							"br.com.caelum.stella" % "caelum-stella-boleto" % "2.1.0",
							"log4j" % "log4j" % "1.2.16",
							"com.typesafe.slick" %% "slick" % "1.0.1",
							"org.xerial" % "sqlite-jdbc" % "3.7.15-M1",
							"org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "container",
      						"org.eclipse.jetty" % "jetty-servlets" % "7.4.5.v20110725" % "container",
      						"org.eclipse.jetty" % "jetty-jsp-2.1" % "7.4.5.v20110725" % "container",
      						"org.mortbay.jetty" % "jsp-2.1-glassfish" % "2.1.v20100127" % "container",
      						"org.seleniumhq.selenium" % "selenium-firefox-driver" % "2.41.0",
      						"org.specs2" %% "specs2" % "2.3.11",
      						"org.json4s" % "json4s-native_2.10" % "3.2.10"
							)

EclipseKeys.withSource := true

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

seq(webSettings :_*)

ivyXML := <dependency org="org.eclipse.jetty.orbit" name="javax.servlet" rev="3.0.0.v201112011016"><artifact name="javax.servlet" type="orbit" ext="jar"/></dependency>

classDirectory in Compile <<= baseDirectory apply ( _ / "src" / "main" / "webapp" / "WEB-INF" / "classes")

javacOptions ++= Seq("-g")
