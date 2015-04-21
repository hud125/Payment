@echo off

call config.bat

cd /d %WORK_DIR%

echo Please wait while downloading jar sources...

mvn dependency:sources > %START_DIR%\download_jar_source.log
