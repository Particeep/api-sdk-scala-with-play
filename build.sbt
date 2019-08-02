name := """api-sdk-scala"""

version := "1.0.0"

scalaVersion := "2.11.12"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  ws, 
  "org.scalatest"     %% "scalatest"                       % "2.2.4"  % "test" withSources(),
  "com.typesafe.play" %% "play-ws"                         % "2.7.0"           withSources() exclude("com.ning", "async-http-client"),
  "com.ning"          %  "async-http-client"               % "1.9.40"          withSources(),
  "com.github.driox"  %% "sorus"                           % "1.2.4"           withSources(),
  "org.slf4j"         %  "slf4j-api"                       % "1.7.21"          withSources(),
  "ai.x"              %% "play-json-extensions"            % "0.40.2"           withSources()
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

// sbt and compiler option
scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-unused",
    "-Ywarn-unused-import"
)

// ~~~~~~~~~~~~~~~~~
//Scalariform config

import scalariform.formatter.preferences._

scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignParameters, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(DanglingCloseParenthesis, Preserve)
