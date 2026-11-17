name := """api-sdk-scala"""

version := "3.0.1"

scalaVersion := "2.13.18"

resolvers += "sonatype-public" at "https://oss.sonatype.org/content/groups/public"

dependencyOverrides ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.22.3",
  "com.google.guava"              % "guava"                % "33.7.2-jre"
)

val playWsStandaloneVersion = "3.0.12"
libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core"            % "2.22.3" withSources (),
  "com.particeep"             %% "play-json-extensions"    % "0.43.1" withSources (),
  "org.playframework"         %% "play"                    % playWsStandaloneVersion withSources (),
  "org.playframework"         %% "play-ahc-ws-standalone"  % playWsStandaloneVersion withSources (),
  "org.playframework"         %% "play-ws-standalone-json" % playWsStandaloneVersion withSources (),
  "org.playframework"         %% "play-ws-standalone-xml"  % playWsStandaloneVersion withSources (),
  "org.scalatest"             %% "scalatest"               % "3.2.20" % Test withSources ()
)
