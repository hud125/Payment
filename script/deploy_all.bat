@echo off

call config.bat

cd /d %WORK_DIR%

start mvn clean deploy -Dmaven.test.skip=true -P %PROFILE%
