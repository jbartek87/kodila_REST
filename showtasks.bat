call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo RUNCRUD FILE has error - breaking work
goto fail


:openbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks

:fail
call %CATALINA_HOME%\bin\shutdown.bat
echo.
echo There were errors

