organization := "com.github.kharigardner"
name := "chatroom4s"
version := "0.1.0a"

crossScalaVersions := Seq("2.11.12", "2.12.4", "2.12.17")
scalaVersion := "2.12.17"

libraryDependencies ++= Seq(
    "org.apache.logging.log4j" % "log4j-api" % "2.12.1",
    "org.apache.logging.log4j" % "log4j-core" % "2.12.1",
)

licenses += ("MIT", url("https://opensource.org/licenses/MIT"))
homepage := Some(url("https://github.com/kharigardner/chatroom4s"))
developers := List(
    Developer(
        "kharigardner",
        "Khari Gardner",
        "@kharigardner",
        url("https://github.com/kharigardner")
    )
)

