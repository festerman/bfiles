name := "BFiles"

version := "0.2.1"

scalaVersion := "2.11.4"


libraryDependencies += "io.netty" % "netty-all" % "4.0.24.Final"

libraryDependencies += "org.specs2" %% "specs2" % "2.4.14" % "test"

libraryDependencies += "org.scalaz.stream" %% "scalaz-stream" % "0.5a"

// Against Scalaz 7.0.6, available for Scala 2.10.4 and 2.11.4
// libraryDependencies += "org.scalaz.stream" %% "scalaz-stream" % "0.6"

// Against Scalaz 7.1, available for Scala 2.10.4 and 2.11.4
// libraryDependencies += "org.scalaz.stream" %% "scalaz-stream" % "0.6a"

  scalacOptions in Test ++= Seq("-Yrangepos")

  // Read here for optional dependencies:
  // http://etorreborre.github.io/specs2/guide/org.specs2.guide.Runners.html#Dependencies

  resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

  resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

