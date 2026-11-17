import BuildUtil.*
import scala.sys.process._

addCommandAlias("ls", "projects")
addCommandAlias("cd", "project")
addCommandAlias("c", "compile")
addCommandAlias("ca", "Test / compile")
addCommandAlias("t", "test")
addCommandAlias("r", "run")
addCommandAlias("grype", "makeBom; runGrype")

val runGrype = Command.command("runGrype") { state =>
  val projectDir = new java.io.File(".").getCanonicalFile
  val bomFiles   = new java.io.File(projectDir, "target").listFiles.filter(_.getName.endsWith(".bom.json"))

  if(bomFiles.isEmpty) {
    sys.error(
      s"No BOM file generated in ${new java.io.File(projectDir, "target").getAbsolutePath}. Run 'makeBom' before 'grype'."
    )
  }

  val bomFile  = bomFiles.minBy(_.getName)
  val grypeExe =
    Option(System.getenv("GRYPE_BIN"))
      .orElse(Option(System.getenv("PATH")).map(_.split(java.io.File.pathSeparator).toSeq).getOrElse(Seq.empty).find(
        p => new java.io.File(p, "grype.exe").exists()
      ).map(p => new java.io.File(p, "grype.exe").getAbsolutePath))
      .getOrElse(
        "C:\\Users\\lasne\\AppData\\Local\\Microsoft\\WinGet\\Packages\\Anchore.Grype_Microsoft.Winget.Source_8wekyb3d8bbwe\\grype.exe"
      )

  val jsonReport = new java.io.File(projectDir, "target/security-reports/grype-report.json")
  val htmlReport = new java.io.File(projectDir, "target/security-reports/grype-report.html")
  val reportsDir = new java.io.File(projectDir, "target/security-reports")
  reportsDir.mkdirs()

  val reportJsonCmd =
    Seq(grypeExe, s"sbom:${bomFile.getAbsolutePath}", "-o", "json", "--file", jsonReport.getAbsolutePath)
  val reportHtmlCmd = Seq(
    grypeExe,
    s"sbom:${bomFile.getAbsolutePath}",
    "-o",
    "template",
    "-t",
    "grype-html.tmpl",
    "--file",
    htmlReport.getAbsolutePath
  )
  val failCheckCmd  = Seq(grypeExe, s"sbom:${bomFile.getAbsolutePath}", "--fail-on", "critical")

  val jsonExit = Process(reportJsonCmd, projectDir).!
  val htmlExit = Process(reportHtmlCmd, projectDir).!

  if(jsonExit != 0 || htmlExit != 0) {
    state.fail
  }

  val failExit = Process(failCheckCmd, projectDir).!
  if(failExit != 0) {
    println("[error] discovered vulnerabilities at or above the severity threshold")
    println(s"[info] Reports generated at ${reportsDir.getAbsolutePath}")
    state.fail
  }

  state
}

commands += runGrype

addCommandAlias(
  "check",
  "scalafmtSbtCheck; scalafmtCheckAll; Test / compile; scalafixAll --check"
)
addCommandAlias(
  "fmt",
  "Test / compile; scalafixAll; scalafmtSbt; scalafmtAll"
)
addCommandAlias(
  "up2date",
  "reload plugins; dependencyUpdates; reload return; dependencyUpdates"
)
onLoadMessage +=
  s"""|
      |╭─────────────────────────────────╮
      |│     List of defined ${styled("aliases")}     │
      |├─────────────┬───────────────────┤
      |│ ${styled("ls")}          │ projects          │
      |│ ${styled("cd")}          │ project           │
      |│ ${styled("c")}           │ compile           │
      |│ ${styled("ca")}          │ compile all       │
      |│ ${styled("t")}           │ test              │
      |│ ${styled("r")}           │ run               │
      |│ ${styled("check")}       │ checks code style │
      |│ ${styled("fmt")}         │ format code       │
      |│ ${styled("up2date")}     │ dependencyUpdates │
      |╰─────────────┴───────────────────╯""".stripMargin
