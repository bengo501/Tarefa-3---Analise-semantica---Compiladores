#!/bin/bash

echo "========================================"
echo "CONFIGURANDO AMBIENTE LINUX"
echo "========================================"

# Atualizar sistema
echo "Atualizando sistema..."
sudo apt update

# Instalar Java
echo "Instalando Java..."
sudo apt install -y openjdk-11-jdk

# Verificar Java
echo "Verificando Java..."
java -version
javac -version

# Dar permissão de execução para yacc.linux
echo "Configurando permissões..."
chmod +x yacc.linux

# Verificar arquivos
echo "Verificando arquivos..."
ls -la *.flex *.y *.jar yacc.linux

echo "========================================"
echo "AMBIENTE CONFIGURADO!"
echo "========================================"
echo "Agora execute: make clean && make"
