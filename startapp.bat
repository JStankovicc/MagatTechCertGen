@echo off

REM Provera da li je Maven instaliran
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo Maven nije instaliran. Molimo instalirajte ga.
    pause
    exit /b 1
)

REM Postavljanje radnog direktorijuma na trenutni
cd /d %~dp0

REM Izvrsavanje mvn clean install
echo Izvrsavam mvn clean install...
call mvn clean install -DskipTests
if %errorlevel% neq 0 (
    echo Greska prilikom izvrsavanja mvn clean install.
    pause
    exit /b 1
)

REM Pokretanje Spring Boot aplikacije
echo Pokrećem Spring Boot aplikaciju...
java -jar target/certGen-0.0.1-SNAPSHOT.jar

echo.
echo Pritisnite bilo koji taster za zatvaranje prozora.
pause >nul
