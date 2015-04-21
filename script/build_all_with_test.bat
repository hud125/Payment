@echo off

call config.bat

cd /d %WORK_DIR%

start mvn clean install --update-snapshots -P %PROFILE%
