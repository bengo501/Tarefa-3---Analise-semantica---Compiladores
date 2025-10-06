@echo off
echo Compilando
javac *.java
echo.
echo Executando teste com corretoStruct.txt
java ParserFinal corretoStruct.txt
echo.
echo Executando teste com erroStruct.txt
java ParserFinal erroStruct.txt
echo.
echo Teste concluido!
pause
