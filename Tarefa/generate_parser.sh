#!/bin/bash
# Script para gerar o parser usando yacc.linux

echo "Gerando Parser.java usando yacc.linux..."

# Copiar arquivos para o WSL
cp exemploSem.y /tmp/
cp yacc.linux /tmp/

# Entrar no WSL e executar yacc
wsl bash -c "
cd /tmp
chmod +x yacc.linux
./yacc.linux -d -J exemploSem.y
echo 'Arquivos gerados:'
ls -la *.java *.c *.h 2>/dev/null || echo 'Nenhum arquivo gerado'
"

# Copiar arquivos de volta se foram gerados
if wsl test -f /tmp/Parser.java; then
    wsl cp /tmp/Parser.java .
    echo "Parser.java copiado com sucesso!"
else
    echo "Erro: Parser.java não foi gerado"
fi

if wsl test -f /tmp/y.tab.c; then
    wsl cp /tmp/y.tab.c .
    echo "y.tab.c copiado com sucesso!"
fi

if wsl test -f /tmp/y.tab.h; then
    wsl cp /tmp/y.tab.h .
    echo "y.tab.h copiado com sucesso!"
fi
