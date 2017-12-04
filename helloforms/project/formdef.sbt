resolvers += Resolver.url("uxforms-sbtplugins", url("https://dl.bintray.com/equalexperts/uxforms-sbtplugins"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.uxforms" % "sbt-formdefinition-plugin" % "0.19")