name := """api-sdk-scala"""

version := "3.0.1"

scalaVersion := "2.13.14"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

// Change this to another test framework if you prefer

val playWsStandaloneVersion = "3.0.5"
val slick_pg_version        = "0.22.2"
libraryDependencies ++= Seq(
  "org.slf4j"            % "slf4j-api"               % "1.7.21" withSources (),
  "com.particeep"       %% "play-json-extensions"    % "0.43.0" withSources (),
  "org.playframework"   %% "play"                    % "3.0.5" withSources (),
  "org.playframework"   %% "play-ahc-ws-standalone"  % playWsStandaloneVersion withSources (),
  "org.playframework"   %% "play-ws-standalone-json" % playWsStandaloneVersion withSources (),
  "org.playframework"   %% "play-ws-standalone-xml"  % playWsStandaloneVersion withSources (),
  "org.scalatest"       %% "scalatest"               % "3.2.18" % Test withSources (),
  "com.github.tminglei" %% "slick-pg"                % slick_pg_version withSources (),
  "com.github.tminglei" %% "slick-pg_play-json"      % slick_pg_version withSources () excludeAll ExclusionRule(
    organization = "com.typesafe.play"
  )
)

// Check Dependancy CVSS config
ThisBuild / dependencyCheckFailBuildOnCVSS         := 9.0f
ThisBuild / dependencyCheckFormats                 := Seq("XML", "HTML")
ThisBuild / dependencyCheckAssemblyAnalyzerEnabled := Option(false)
ThisBuild / dependencyCheckSuppressionFiles        := Seq(baseDirectory.value / "dependency-check-suppressions.xml")
dependencyCheckOutputDirectory                     := Some(baseDirectory.value / "target/security-reports")
