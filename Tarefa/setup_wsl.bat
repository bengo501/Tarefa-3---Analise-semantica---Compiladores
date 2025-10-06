@echo off
echo ========================================
echo PREPARANDO ARQUIVOS PARA WSL
echo ========================================

echo Copiando arquivos originais...
copy "..\Conteudos\exemploSem_v0_array\exemploSem_v0_array\exemploSem.flex" .
copy "..\Conteudos\exemploSem_v0_array\exemploSem_v0_array\exemploSem.y" .
copy "..\Conteudos\exemploSem_v0_array\exemploSem_v0_array\JFlex.jar" .
copy "..\Conteudos\exemploSem_v0_array\exemploSem_v0_array\yacc.linux" .
copy "..\Conteudos\exemploSem_v0_array\exemploSem_v0_array\Makefile" .

echo Arquivos copiados com sucesso!
echo.
echo Agora execute no WSL:
echo 1. wsl
echo 2. cd /mnt/f/Game\ Projects/Godot\ 4/Tarefa-3---Analise-semantica---Compiladores/Tarefa
echo 3. ./setup_linux.sh
echo.
pause
