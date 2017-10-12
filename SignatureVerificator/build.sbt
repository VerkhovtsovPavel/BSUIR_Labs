val applicationName = "SignatureVerification"
name := applicationName
version := "1.0"
scalaVersion := "2.12.3"
artifactPath in Compile in packageBin <<=
  baseDirectory { base => base / "jar" / s"$applicationName.jar" }

