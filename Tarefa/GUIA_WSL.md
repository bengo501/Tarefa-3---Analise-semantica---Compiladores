# 🐧 Guia de Execução no WSL

## 📋 Passo a Passo

### 1. Preparar arquivos no Windows
```bash
# Execute no PowerShell:
setup_wsl.bat
```

### 2. Entrar no WSL
```bash
wsl
```

### 3. Navegar para a pasta
```bash
cd "/mnt/f/Game Projects/Godot 4/Tarefa-3---Analise-semantica---Compiladores/Tarefa"
```

### 4. Configurar ambiente Linux
```bash
chmod +x setup_linux.sh
./setup_linux.sh
```

### 5. Compilar e testar
```bash
# Usar o Makefile para structs
cp Makefile_struct Makefile
make clean
make

# Testar
make test
```

## 🔧 Arquivos Modificados

### exemploSem_struct.flex
- Adicionado token `FLOAT`
- Adicionado operador `.` para acesso a campos
- Adicionado palavra-chave `struct`

### exemploSem_struct.y
- Adicionadas regras para `structDecl`
- Adicionadas regras para `campoList` e `campoDecl`
- Adicionado suporte a tipos de struct em `type`
- Adicionado acesso a campos com `exp '.' IDENT`
- Adicionados tipos `Tp_FLOAT` e `Tp_STRUCT`

## 🎯 Resultado Esperado

A saída deve ser exatamente como no enunciado:

```
ident      Classe         Escopo      Tipo
-----------------------------------------------
_erro_     TipoBase       -           null
int        TipoBase       -           null
float      TipoBase       -           null
bool       TipoBase       -           null
string     TipoBase       -           null
array      TipoBase       -           null
struct     TipoBase       -           null
i          VarGlobal      -           int
x          VarGlobal      -           float
b          VarGlobal      -           boolean
DATA       NomeStruct     -           struct
  dia      CampoStruct    DATA        int
  mes      CampoStruct    DATA        int
  ano      CampoStruct    DATA        int
ALUNO      NomeStruct     -           struct
  matricula CampoStruct   ALUNO       int
  nome     CampoStruct    ALUNO       string
  dnasc    CampoStruct    ALUNO       DATA
  ativo    CampoStruct    ALUNO       boolean
alu1       VarGlobal      -           ALUNO
alu2       VarGlobal      -           ALUNO
d1         VarGlobal      -           DATA
d2         VarGlobal      -           DATA
```

## 🚨 Solução de Problemas

### Se der erro de permissão:
```bash
chmod +x yacc.linux
```

### Se der erro de Java:
```bash
sudo apt install openjdk-11-jdk
```

### Se der erro de compilação:
```bash
make clean
make
```
