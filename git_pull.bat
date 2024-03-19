@echo off

REM Proveri da li je git instaliran
where git >nul 2>&1
if %errorlevel% neq 0 (
    echo Git nije instaliran. Molimo instalirajte ga.
    pause
    exit /b 1
)

REM Proveri da li je trenutni direktorijum Git repozitorijum
if not exist .git (
    echo Ovaj direktorijum nije Git repozitorijum.
    pause
    exit /b 1
)

REM Izvrši git pull
git pull origin main

echo.
echo Pritisnite bilo koje dugme za zatvaranje prozora.
pause >nul
