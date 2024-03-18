name := """api-sdk-scala"""

version := "1.0.0"

scalaVersion := "2.13.12"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"                       % "3.1.0"  % "test" withSources(),
  "org.slf4j"         %  "slf4j-api"                       % "1.7.21"          withSources(),
  "ai.x"              %% "play-json-extensions" 		       % "0.42.0" 		     withSources(),
  "com.typesafe.play" %% "play-ws"                         % "2.8.1"           withSources(),
  "com.typesafe.play" %% "play-ahc-ws-standalone"          % "2.1.2"           withSources(),
  "com.opentable.components" %  "otj-pg-embedded"    % "0.13.3"   % "test" withSources()
)

// sbt and compiler option
scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-unused"
)

// ~~~~~~~~~~~~~~~~~
//Scalariform config

import scalariform.formatter.preferences._

scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignParameters, true)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DanglingCloseParenthesis, Preserve)
