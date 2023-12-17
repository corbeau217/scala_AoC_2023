name := "scala_AoC_2023"

version := "0.1"

organization := "comp.mq.edu.au"

// Scala compiler settings

scalaVersion := "2.12.18"

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
        "org.bitbucket.inkytonik.kiama" %% "kiama" % "2.1.0",
        "org.bitbucket.inkytonik.kiama" %% "kiama" % "2.1.0" % "test" classifier ("tests"),
        "junit" % "junit" % "4.12" % "test",
        "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",
        "org.scalatest" %% "scalatest" % "3.0.3" % "test"
    )

console / initialCommands := """
      |import org.bitbucket.inkytonik.kiama.util._
      |import org.bitbucket.inkytonik.kiama.output.PrettyPrinter._
    """.trim.stripMargin
