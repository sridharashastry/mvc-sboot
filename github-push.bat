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
for /f "delims=" %%A in ('git status --porcelain') do echo %%A
echo These files will be staged for commit.
set /p proceed="Do you want to continue? (y/n): "
if /i "%proceed%"=="y" (
    git add .
    git commit -m "%comment%"
    git push
) else (
    echo Operation canceled.
    goto end
)

:end
echo Operation complete.
echo Press any key to exit...
pause >nul
