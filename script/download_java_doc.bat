@echo off

call config.bat

cd /d %WORK_DIR%

echo Please wait while downloading java docs...

mvn dependency:resolve -Dclassifier=javadoc > %START_DIR%\download_java_doc.log
