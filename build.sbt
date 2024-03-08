name := """api-sdk-scala"""

version := "1.1.0"

scalaVersion := "2.13.12"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.slf4j"          % "slf4j-api"              % "1.7.21" withSources (),
  "com.particeep"     %% "play-json-extensions"   % "0.43.0" withSources (),
  "com.typesafe.play" %% "play-ws"                % "2.9.2" withSources (),
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.2.5" withSources (),
  "org.scalatest"     %% "scalatest"              % "3.2.18" % Test withSources ()
)

// Check Dependancy CVSS config
ThisBuild / dependencyCheckFailBuildOnCVSS         := 9.0f
ThisBuild / dependencyCheckFormats                 := Seq("XML", "HTML")
ThisBuild / dependencyCheckAssemblyAnalyzerEnabled := Option(false)
ThisBuild / dependencyCheckSuppressionFiles        := Seq(baseDirectory.value / "dependency-check-suppressions.xml")
dependencyCheckOutputDirectory                     := Some(baseDirectory.value / "target/security-reports")
