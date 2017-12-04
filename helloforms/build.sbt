
val basePackage = "com.example.form" // TODO: Change this to be the package in which your FormDefinitionFactory and Activator classes live

lazy val root = (project in file("."))
  .enablePlugins(SbtOsgi, FormDefinitionPlugin, BuildInfoPlugin)
  .settings(
    buildInfoPackage := s"$basePackage.build",
    buildInfoKeys := Seq[BuildInfoKey](
      name,
      "artifact" -> (artifactPath in (Compile, packageBin)).value
    ),
    buildInfoObject := "MyFormDefinitionBuildInfo"
  )

scalaVersion := "2.11.7"

organization := "com.example" // TODO: Change this to be your organisation

name := "EEtest" // TODO: Change this to be the path on which your form will be deployed

formDefinitionClass := s"$basePackage.MyFormDefinitionFactory" // TODO: Change this to be the fully qualified class name of your FormDefinitionFactory

test <<= (test in Test) dependsOn OsgiKeys.bundle

libraryDependencies ++= {
  Seq(
    "com.uxforms" %% "uxforms-dsl" % "15.13.+",
    "com.uxforms" %% "test" % "15.13.+" % Test,
    "org.scalatest" %% "scalatest" % "2.2.4" % Test
  )
}

// We must export our form definition factory and activator's package to the OSGi context
// so that UX Forms can load and execute this form definition.
OsgiKeys.exportPackage := Seq(s"$basePackage.*")

OsgiKeys.bundleActivator := Some(s"$basePackage.Activator")
