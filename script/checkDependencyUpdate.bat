@echo off

call config.bat

cd /d %WORK_DIR%

echo Please wait while checking dependency update...

mvn versions:display-dependency-updates -DallowSnapshots=true -DgenerateBackupPoms=false > %START_DIR%\dependency-updates.log

echo.
echo Done.
echo.

cd /d %START_DIR%
