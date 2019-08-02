resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Typesafe repository plugin" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"

resolvers += "scalaz-bintray" at "https://de.bintray.com/scalaz/releases/"

resolvers += Classpaths.sbtPluginReleases

// code plugins

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.0")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0" excludeAll(
  ExclusionRule(organization = "com.danieltrinh")))

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

// scala lint tool : https://github.com/puffnfresh/wartremover
//addSbtPlugin("org.brianmckenna" % "sbt-wartremover" % "0.13")

// wait for next release
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.3")
