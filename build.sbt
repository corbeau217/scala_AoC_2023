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
        "junit" % "junit" % "4.13" % "test",
        "org.scalacheck" %% "scalacheck" % "1.17.0" % "test",
        "org.scalatest" %% "scalatest" % "3.2.17" % "test",
        "org.scalatestplus" %% "scalacheck-1-17" % "3.2.17.0" % "test",
        "org.scalatestplus" %% "junit-4-13" % "3.2.17.0" % "test",
        // "com.github.nscala-time" %% "nscala-time" % "2.32.0",
        // "org.scalaj" %% "scalaj-time" % "0.7",
        "org.bitbucket.inkytonik.kiama" %% "kiama" % "2.5.1",
        // "org.bitbucket.inkytonik.kiama" %% "kiama" % "2.5.1" % "test" classifier ("tests"),
        "org.bitbucket.inkytonik.kiama" %% "kiama-extras" % "2.5.1" % "test" classifier ("tests")
    )

console / initialCommands := """
      |import org.bitbucket.inkytonik.kiama.util._
      |import org.bitbucket.inkytonik.kiama.output.PrettyPrinter._
    """.trim.stripMargin
