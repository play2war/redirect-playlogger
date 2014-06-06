organization := "com.github.play2war.ext"

name := "redirect-playlogger"

crossScalaVersions := Seq("2.9.1", "2.10.4", "2.11.1")

scalaVersion := "2.10.0"

resolvers += "Typesafe releases" at "http://repo.typesafe.com/typesafe/releases"

autoScalaLibrary := false

libraryDependencies <+= (scalaVersion) { scalaVersion: String =>
  if (scalaVersion.contains("2.11")) {
    "com.typesafe.play" %% "play" % "2.3.0" % "provided->default"
  } else {
    val playVersion = if (scalaVersion.contains("2.9")) "2.0.4"
                      else "2.1.0"
    "play" %% "play" % playVersion % "provided->default"
  }
} 

publishTo <<= (version) { version: String =>
  val nexus = "https://oss.sonatype.org/"
  val repo = if (version.trim.endsWith("SNAPSHOT")) Resolver.url("snapshot",  url(nexus + "content/repositories/snapshots"))
//  val repo = if (version.trim.endsWith("SNAPSHOT")) Resolver.file("file",  file(Path.userHome.absolutePath + "/.ivy2/publish"))
//             else Resolver.file("file",  file(Path.userHome.absolutePath + "/.ivy2/publish"))
             else Resolver.url("release",  url(nexus + "service/local/staging/deploy/maven2"))
  Some(repo)
}

publishMavenStyle := true

releaseSettings

pomIncludeRepository := { _ => false }

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

homepage := Some(url("https://github.com/dlecan/redirect-playlogger"))

pomExtra := (
  <scm>
    <url>git@github.com:dlecan/redirect-playlogger.git</url>
    <connection>scm:git:git@github.com:dlecan/redirect-playlogger.git</connection>
  </scm>
  <developers>
    <developer>
      <id>dlecan</id>
      <name>Damien Lecan</name>
      <email>dev@dlecan.com</email>
    </developer>
  </developers>)