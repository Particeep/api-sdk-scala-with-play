name := """api-sdk-scala"""

version := "3.0.1"

scalaVersion := "2.13.14"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

// Change this to another test framework if you prefer

val playWsStandaloneVersion = "3.0.5"
libraryDependencies ++= Seq(
  "org.slf4j"                  % "slf4j-api"               % "1.7.21" withSources (),
  "com.fasterxml.jackson.core" % "jackson-core"            % "2.15.0" withSources (),
  "com.particeep"             %% "play-json-extensions"    % "0.43.0" withSources (),
  "org.playframework"         %% "play"                    % "3.0.5" withSources (),
  "org.playframework"         %% "play-ahc-ws-standalone"  % playWsStandaloneVersion withSources (),
  "org.playframework"         %% "play-ws-standalone-json" % playWsStandaloneVersion withSources (),
  "org.playframework"         %% "play-ws-standalone-xml"  % playWsStandaloneVersion withSources (),
  "org.scalatest"             %% "scalatest"               % "3.2.18" % Test withSources ()
)

// Check Dependancy CVSS config
ThisBuild / dependencyCheckFailBuildOnCVSS         := 4.8f
ThisBuild / dependencyCheckFormats                 := Seq("XML", "HTML")
ThisBuild / dependencyCheckAssemblyAnalyzerEnabled := Option(false)
dependencyCheckOutputDirectory                     := Some(baseDirectory.value / "target/security-reports")
