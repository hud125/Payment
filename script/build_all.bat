@echo off

call config.bat

cd /d %WORK_DIR%

start mvn clean install -Dmaven.test.skip=true --update-snapshots -P %PROFILE%
