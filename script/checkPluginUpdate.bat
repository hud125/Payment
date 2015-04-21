@echo off

call config.bat

cd /d %WORK_DIR%

echo Please wait while checking plugin update...

mvn versions:display-plugin-updates -DallowSnapshots=true -DgenerateBackupPoms=false > %START_DIR%\plugin-updates.log

echo.
echo Done.
echo.

cd /d %START_DIR%
