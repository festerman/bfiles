name := "BFiles"

version := "0.1"

scalaVersion := "2.10.4"


libraryDependencies ++= Seq (
    "org.specs2" %% "specs2" % "2.3.10" % "test"
)

// libraryDependencies ++= Seq (
// 	"io.netty" % "netty" % "4.0.18.Final"
//)

  scalacOptions in Test ++= Seq("-Yrangepos")

  // Read here for optional dependencies:
  // http://etorreborre.github.io/specs2/guide/org.specs2.guide.Runners.html#Dependencies

  resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)