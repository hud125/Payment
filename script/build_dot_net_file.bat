@echo off

call config.bat

cd /d %SHARPEN_DIR%

start ant -f run-sharpen.xml copy-generated
