resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "scalaz-bintray" at "https://de.bintray.com/scalaz/releases/"

resolvers += "Typesafe repository plugin" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"

resolvers += "scalaz-bintray" at "https://de.bintray.com/scalaz/releases/"

resolvers += Classpaths.sbtPluginReleases

// code plugins

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0" excludeAll(
  ExclusionRule(organization = "com.danieltrinh")))

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.1")

// scala lint tool : https://github.com/puffnfresh/wartremover
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.4.5")

// wait for next release
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.3")
