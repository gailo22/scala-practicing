version in ThisBuild := "0.0.1"

organization in ThisBuild := "gailo22"

scalaVersion in ThisBuild := "2.12.4"

triggeredMessage in ThisBuild := Watched.clearWhenTriggered

// addCommandAlias("root", "project todo")

// addCommandAlias("cd", "project")

// shellPrompt := (_ => fancyPrompt(name.value))

// mainClass in (Compile,run) := Some("Main")

scalacOptions += "-Ypartial-unification"
libraryDependencies += "org.typelevel" %% "cats-core" % "1.4.0"
