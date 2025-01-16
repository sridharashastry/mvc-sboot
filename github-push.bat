@echo off
setlocal

:confirm
set /p confirm="Do you want to proceed with git add and push? (y/n): "
if /i "%confirm%"=="y" goto getMessage
if /i "%confirm%"=="n" goto end
echo Invalid input. Please enter 'y' or 'n'.
goto confirm

:getMessage
set /p comment="Enter your commit message: "
git add .
git commit -m "%comment%"
git push
goto end

:end
echo Operation complete.
echo Press any key to exit...
pause >nul
