@echo off
echo ========================================
echo TESTE MANUAL - TAREFA 3
echo ========================================
echo.

echo 1. Verificando Java...
java -version
echo.

echo 2. Compilando projeto...
javac *.java
echo.

echo 3. Testando com corretoStruct.txt...
java ParserFinal corretoStruct.txt
echo.

echo 4. Testando com erroStruct.txt...
java ParserFinal erroStruct.txt
echo.

echo ========================================
echo TESTE CONCLUIDO!
echo ========================================
pause
