resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Typesafe repository plugin" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"

resolvers += "bitbucket-release" at "https://bitbucket.org/particeep/particeep-repository/raw/master/repository/"

resolvers += Classpaths.sbtPluginReleases

// code plugins

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0" excludeAll ExclusionRule(organization =
  "com.danieltrinh"
))

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.4.4")

// scala lint tool : https://github.com/puffnfresh/wartremover
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "3.6.2")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.14.9")

// Generate a CycloneDX SBOM with `sbt makeBom` (scanned by Grype in CI)
// https://github.com/sbt/sbt-sbom
addSbtPlugin("com.github.sbt" % "sbt-sbom" % "0.4.0")

// This is an issue with lib / sbt plugin who don't have the same version for scala-xml
// Binary compatility is "nearly" ok between scala-xml version
// And this impact only sbt coverage test in jenkins
// cf. https://github.com/sbt/sbt/issues/6997
ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
