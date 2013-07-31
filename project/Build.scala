import sbt._
import Keys._
import play.Project._
import de.johoop.findbugs4sbt.FindBugs._
import de.johoop.jacoco4sbt._
import JacocoPlugin._
import java.io.File

object ApplicationBuild extends Build {

  val appName         = "play-example-wms"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "mysql" % "mysql-connector-java" % "5.1.21",
    "org.webjars" % "webjars-play" % "2.1.0",
    "org.webjars" % "bootstrap" % "2.3.1"
  )

  // Update settings for findbugs and jacoco SBT plugins.
  lazy val s = Defaults.defaultSettings ++ findbugsSettings ++ Seq(jacoco.settings:_*)

  val main = play.Project(appName, appVersion, appDependencies, settings = s).settings(

    // Add 'pmd' command to Play console. 
    // Configuration file: project/pmd-ruleset.xml
    // Output file: target/pmd/pmd-report.txt
    PmdSettings.pmdTask,

    // Add 'checkstyle' command to Play console. 
    // Configuration file: project/checkstyle-config.xml
    // Output file: target/checkstyle/checkstyle-report.txt
    CheckstyleSettings.checkstyleTask,

    // Add 'api-doc' command (JavaDoc + ScalaDoc) to Play console. 
    // Output directory: target/doc/api
    ApiDocSettings.apiDocTask,

    // Add the 'findbugs' command to Play console.
    // Configuration file: project/findbugs-excludefilter.xml
    // Output file: target/findbugs/findbugs.xml
    // You want the report name file extension to match the report type. 
    findbugsReportType := de.johoop.findbugs4sbt.ReportType.Xml,
    findbugsReportName := "findbugs.xml",
    findbugsTargetPath <<= target (_ / "findbugs"),
    findbugsExcludeFilters <<= baseDirectory { base => Some(scala.xml.XML.loadFile(BuildPaths.projectStandard(base) / "findbugs-excludefilter.xml")) },

    // Add the 'jacoco:cover' command to Play console. 
    // Output file: target/jacoco/html/index.html
    parallelExecution      in jacoco.Config := false,
    jacoco.outputDirectory in jacoco.Config := file("target/jacoco"),
    jacoco.reportFormats   in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8")),
    jacoco.excludes        in jacoco.Config := Seq("views*", "*Routes*", "controllers*routes*", "controllers*Reverse*", "controllers*javascript*", "controller*ref*")
  )

}
