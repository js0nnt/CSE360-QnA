param([switch]$CompileOnly)

$ErrorActionPreference = 'Stop'

$project = $PSScriptRoot
$javaHomes = @()
if ($env:JAVA_HOME) { $javaHomes += $env:JAVA_HOME }
$javaHomes += @(Get-ChildItem (Join-Path $HOME '.jdks') -Directory -Filter 'openjdk-26*' -ErrorAction SilentlyContinue | ForEach-Object FullName)
$javaHomes += @(Get-ChildItem 'C:\Program Files\Java' -Directory -Filter 'jdk-26*' -ErrorAction SilentlyContinue | ForEach-Object FullName)

$jdk = $null
foreach ($candidate in $javaHomes) {
    $javacCandidate = Join-Path $candidate 'bin\javac.exe'
    if (-not (Test-Path $javacCandidate)) { continue }
    $version = & $javacCandidate -version 2>&1
    if ($version -match '^javac (\d+)' -and [int]$Matches[1] -ge 26) {
        $jdk = $candidate
        break
    }
}
if (-not $jdk) { throw 'Java 26 or newer is required. Install a JDK and set JAVA_HOME to its folder.' }

$fxHomes = @()
if ($env:JAVAFX_HOME) { $fxHomes += $env:JAVAFX_HOME }
$fxHomes += @(Get-ChildItem (Join-Path $HOME 'Downloads') -Directory -Filter 'javafx-sdk-26*' -ErrorAction SilentlyContinue | ForEach-Object FullName)
$fx = $null
foreach ($candidate in $fxHomes) {
    $lib = Join-Path $candidate 'lib'
    if (Test-Path (Join-Path $lib 'javafx.controls.jar')) {
        $fx = $lib
        break
    }
}
if (-not $fx) { throw 'JavaFX SDK 26 is required. Download it and set JAVAFX_HOME to the extracted SDK folder.' }

$sqlite = Join-Path $project 'lib\sqlite-jdbc-3.46.1.3.jar'
$output = Join-Path $project 'out\manual'
$sources = @(Get-ChildItem (Join-Path $project 'src\java') -Recurse -Filter '*.java' | ForEach-Object FullName)
New-Item -ItemType Directory -Force $output | Out-Null

& (Join-Path $jdk 'bin\javac.exe') --module-path $fx --add-modules javafx.controls,javafx.fxml -cp $sqlite -d $output $sources
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
if ($CompileOnly) { return }

$classpath = "$output;$(Join-Path $project 'src\resources');$sqlite"
Push-Location $project
try {
    & (Join-Path $jdk 'bin\java.exe') --enable-native-access=javafx.graphics,ALL-UNNAMED --module-path $fx --add-modules javafx.controls,javafx.fxml -cp $classpath com.template.Launcher
    exit $LASTEXITCODE
}
finally {
    Pop-Location
}
