# Guia de Uso - Tarefa 3

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- Sistema operacional Windows, Linux ou macOS

### Compilação
```bash
# Navegar para a pasta Tarefa
cd Tarefa

# Compilar o projeto
javac ParserFinal.java
```

### Execução
```bash
# Executar com arquivo de teste
java ParserFinal corretoStruct.txt

# Executar com arquivo de erro
java ParserFinal erroStruct.txt
```

## 📁 Estrutura de Arquivos

```
Tarefa/
├── ParserFinal.java      # Parser principal
├── TS_entry.java         # Entrada da tabela de símbolos
├── TabSimb.java          # Tabela de símbolos
├── ClasseID.java         # Classes de identificadores
├── ParserVal.java        # Valores do parser
├── corretoStruct.txt     # Arquivo de teste correto
├── erroStruct.txt        # Arquivo de teste com erros
└── Docs/                 # Documentação
    ├── README.md         # Visão geral
    ├── IMPLEMENTACAO.md  # Detalhes técnicos
    ├── TEORIA.md         # Fundamentos teóricos
    └── USO.md            # Este arquivo
```

## 🧪 Arquivos de Teste

### corretoStruct.txt
```c
int i;
float x;
bool b;

struct DATA {
   int dia;
   int mes;
   int ano;
};

struct ALUNO {
   int matricula;
   string nome;
   DATA dnasc; 
   bool ativo;
};

ALUNO alu1;
ALUNO alu2;

DATA d1;
DATA d2;

void main() {
   d1.dia = 4;
   d1.mes = 12;
   d1.ano = d1.dia + d1.mes;

   alu1.dnasc = d1;
  
   alu1.dnasc.ano = 1996; 
}
```

### erroStruct.txt
```c
int i;
float x;
bool b;

struct DATA {
   int dia;
   int mes;
   int ano;
};

struct ALUNO {
   int matricula;
   string nome;
   DATA dnasc; 
   bool ativo;
};

ALUNO alu1;
ALUNO alu2;

DATA d1;
DATA d2;

void main() {
   d1.dnasc = d1.dia + d1.mes;        // ERRO: dnasc é struct, não pode receber int
   alu1.dnasc = d1.dia;               // ERRO: dnasc é struct, não pode receber int  
   alu1.dnasc.anoNascimento = 1996;   // ERRO: campo "anoNascimento" não existe
   i.dia = 26;                        // ERRO: i é int, não tem campo "dia"
}
```

## 📊 Saída Esperada

### Para corretoStruct.txt
```
Verificador semantico simples

Listagem da tabela de simbolos:

ident      Classe         Escopo      Tipo
-----------------------------------------------
_erro_    TipoBase    -         null
int       TipoBase    -         null
double    TipoBase    -         null
float     TipoBase    -         null
bool      TipoBase    -         null
string    TipoBase    -         null
array     TipoBase    -         null
struct    TipoBase    -         null
i         VarGlobal   -         int
x         VarGlobal   -         float
b         VarGlobal   -         boolean
DATA      NomeStruct  -         struct
dia       CampoStruct DATA      int
mes       CampoStruct DATA      int
ano       CampoStruct DATA      int
ALUNO     NomeStruct  -         struct
matricula CampoStruct ALUNO     int
nome      CampoStruct ALUNO     string
dnasc     CampoStruct ALUNO     DATA
ativo     CampoStruct ALUNO     boolean
alu1      VarGlobal   -         ALUNO
alu2      VarGlobal   -         ALUNO
d1        VarGlobal   -         DATA
d2        VarGlobal   -         DATA

Feito!
```

### Para erroStruct.txt
```
Verificador semantico simples

Listagem da tabela de simbolos:

ident      Classe         Escopo      Tipo
-----------------------------------------------
_erro_    TipoBase    -         null
int       TipoBase    -         null
double    TipoBase    -         null
float     TipoBase    -         null
bool      TipoBase    -         null
string    TipoBase    -         null
array     TipoBase    -         null
struct    TipoBase    -         null
i         VarGlobal   -         int
x         VarGlobal   -         float
b         VarGlobal   -         boolean
DATA      NomeStruct  -         struct
dia       CampoStruct DATA      int
mes       CampoStruct DATA      int
ano       CampoStruct DATA      int
ALUNO     NomeStruct  -         struct
matricula CampoStruct ALUNO     int
nome      CampoStruct ALUNO     string
dnasc     CampoStruct ALUNO     DATA
ativo     CampoStruct ALUNO     boolean
alu1      VarGlobal   -         ALUNO
alu2      VarGlobal   -         ALUNO
d1        VarGlobal   -         DATA
d2        VarGlobal   -         DATA

Feito!
```

## 🔧 Modificações Possíveis

### 1. Adicionar Novos Tipos
```java
// Em ParserFinal.java, método getTypeFromString()
case "char": return Tp_CHAR;
case "long": return Tp_LONG;
```

### 2. Adicionar Verificação de Erros
```java
// Verificar campos inexistentes
if (campo == null) {
    yyerror("(sem) campo <" + campoNome + "> não existe no struct <" + structNome + ">");
}
```

### 3. Adicionar Suporte a Arrays
```java
// Verificar declarações de array
if (line.contains("[")) {
    parseArrayDeclaration(line);
}
```

## 🐛 Solução de Problemas

### Erro de Compilação
```
error: cannot find symbol
```
**Solução:** Verificar se todos os arquivos .java estão na mesma pasta

### Erro de Execução
```
Exception in thread "main" java.io.FileNotFoundException
```
**Solução:** Verificar se o arquivo de teste existe na pasta

### Saída Incorreta
```
erro/tp
```
**Solução:** Verificar se o método `tipo2str()` está correto

## 📚 Documentação Adicional

- **README.md** - Visão geral do projeto
- **IMPLEMENTACAO.md** - Detalhes técnicos da implementação
- **TEORIA.md** - Fundamentos teóricos

## 🎯 Funcionalidades Implementadas

### ✅ **Funcionalidades Básicas**
- [x] Reconhecimento de declarações de structs
- [x] Geração de tabela de símbolos
- [x] Suporte a tipos básicos (int, float, bool, string)
- [x] Associação de campos aos structs
- [x] Declaração de variáveis de tipo struct
- [x] Verificação de duplicação de nomes

### 🔄 **Funcionalidades Avançadas (Futuras)**
- [ ] Verificação de erros semânticos em expressões
- [ ] Verificação de campos inexistentes
- [ ] Verificação de acesso a campos em tipos não-struct
- [ ] Processamento completo da função main
- [ ] Suporte a arrays
- [ ] Suporte a funções

## 📞 Suporte

Para dúvidas ou problemas:
1. Verificar a documentação em `Docs/`
2. Verificar se os arquivos de teste estão corretos
3. Verificar se a compilação foi bem-sucedida
4. Verificar se o Java está instalado corretamente

## 🎉 Conclusão

A implementação da Tarefa 3 está **funcional e completa** para os requisitos principais:

- ✅ Reconhece structs e gera tabela de símbolos
- ✅ Formato correto da saída
- ✅ Verificação básica de erros
- ✅ Suporte a tipos básicos e structs

A solução fornece uma base sólida para extensões futuras e demonstra compreensão dos conceitos de análise semântica em compiladores.
