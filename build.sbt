name := "scala_AoC_2023"

version := "0.1"

organization := "comp.mq.edu.au"

// Scala compiler settings

scalaVersion := "3.3.1"

scalacOptions :=
    Seq(
        "-deprecation",
        "-feature",
        "-unchecked",
        "-Xcheckinit",
        // "-Xfatal-warnings",
        "-Xlint:-stars-align,-unused,_"
    )

// Interactive settings

logLevel := Level.Info

shellPrompt := {
    state =>
        Project.extract(state).currentRef.project + " " + version.value +
            " " + scalaVersion.value + "> "
}

// Execution

Test / parallelExecution := false

// Dependencies

libraryDependencies ++=
    Seq (
        // "com.github.nscala-time" %% "nscala-time" % "2.32.0",
        // "org.scalaj" %% "scalaj-time" % "0.7",
        "org.bitbucket.inkytonik.kiama" %% "kiama" % "2.5.1",
        "org.bitbucket.inkytonik.kiama" %% "kiama" % "2.5.1" % "test" classifier ("tests"),
        "junit" % "junit" % "4.12" % "test",
        "org.scalacheck" %% "scalacheck" % "1.15.4" % "test",
        "org.scalatest" %% "scalatest" % "3.2.17" % "test"
    )

console / initialCommands := """
      |import org.bitbucket.inkytonik.kiama.util._
      |import org.bitbucket.inkytonik.kiama.output.PrettyPrinter._
    """.trim.stripMargin
