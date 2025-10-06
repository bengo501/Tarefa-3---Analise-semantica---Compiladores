# Fundamentos Teóricos - Análise Semântica para Structs

## 📚 Conceitos Fundamentais

### 1. Análise Semântica

A **análise semântica** é a terceira fase de um compilador, após a análise léxica e sintática. Sua função é verificar se o programa fonte está semanticamente correto, ou seja, se faz sentido do ponto de vista da linguagem.

#### Objetivos da Análise Semântica:
- **Verificação de tipos:** Garantir compatibilidade entre tipos
- **Verificação de declarações:** Verificar se variáveis/funções estão declaradas
- **Verificação de escopo:** Verificar se identificadores são usados no escopo correto
- **Verificação de semântica:** Verificar regras específicas da linguagem

### 2. Tabela de Símbolos

A **tabela de símbolos** é uma estrutura de dados fundamental na análise semântica que armazena informações sobre os identificadores declarados no programa.

#### Informações Armazenadas:
- **Nome do identificador**
- **Tipo de dados**
- **Escopo de declaração**
- **Classe do símbolo** (variável, função, tipo, etc.)
- **Atributos específicos** (valor, endereço, etc.)

#### Operações Básicas:
- **Inserção:** Adicionar novo símbolo
- **Busca:** Procurar símbolo existente
- **Atualização:** Modificar informações de símbolo existente

### 3. Structs (Estruturas de Dados)

**Structs** são tipos de dados compostos que agrupam variáveis de diferentes tipos sob um único nome.

#### Características:
- **Campos:** Variáveis individuais dentro do struct
- **Tipo composto:** O struct é um tipo de dados
- **Escopo:** Campos pertencem ao escopo do struct
- **Instanciação:** Variáveis podem ser declaradas do tipo struct

## 🏗️ Arquitetura da Solução

### 1. Estrutura de Dados

#### ClasseID (Enum)
```java
public enum ClasseID {
    TipoBase,      // Tipos básicos (int, float, bool, string)
    VarGlobal,     // Variáveis globais
    NomeStruct,    // Nomes de structs
    CampoStruct,   // Campos dentro de structs
    NomeFuncao,    // Nomes de funções
    NomeParam,     // Parâmetros de funções
    VarLocal       // Variáveis locais
}
```

#### TS_entry (Entrada da Tabela)
```java
public class TS_entry {
    private String id;           // Nome do identificador
    private ClasseID classe;     // Classe do símbolo
    private TS_entry tipo;       // Tipo de dados
    private String escopo;       // Escopo (para campos de struct)
    private int nroElementos;    // Número de elementos (para arrays)
    private TS_entry tipoBase;   // Tipo base (para arrays)
}
```

### 2. Algoritmo de Parsing

#### Fase 1: Processamento de Structs
```
Para cada linha do arquivo:
    Se linha começa com "struct ":
        1. Extrair nome do struct
        2. Verificar se já existe
        3. Inserir na tabela de símbolos
        4. Processar campos do struct
```

#### Fase 2: Processamento de Variáveis
```
Para cada linha do arquivo:
    Se linha é declaração de variável:
        1. Extrair tipo e nome
        2. Resolver tipo (básico ou struct)
        3. Verificar se variável já existe
        4. Inserir na tabela de símbolos
```

#### Fase 3: Verificação Semântica
```
Para cada símbolo:
    1. Verificar se tipo existe
    2. Verificar duplicação de nomes
    3. Verificar escopo correto
    4. Reportar erros encontrados
```

### 3. Resolução de Tipos

#### Tipos Básicos
```java
switch (tipoStr) {
    case "int": return Tp_INT;
    case "float": return Tp_FLOAT;
    case "bool": return Tp_BOOL;
    case "string": return Tp_STRING;
}
```

#### Tipos Struct
```java
// Buscar na tabela de símbolos
TS_entry nodo = ts.pesquisa(tipoStr);
if (nodo != null && nodo.getClasse() == ClasseID.NomeStruct) {
    return nodo;
}
```

## 🔍 Verificação Semântica

### 1. Verificação de Declarações

#### Duplicação de Nomes
```java
TS_entry nodo = ts.pesquisa(nome);
if (nodo != null) {
    yyerror("(sem) " + tipo + " <" + nome + "> já declarado");
}
```

#### Tipos Não Declarados
```java
TS_entry tipo = getTypeFromString(tipoStr);
if (tipo == Tp_ERRO) {
    yyerror("(sem) tipo <" + tipoStr + "> nao declarado");
}
```

### 2. Verificação de Escopo

#### Campos de Struct
```java
// Campos são associados ao struct correto
TS_entry campo = new TS_entry(campoName, tipo, ClasseID.CampoStruct, 0, null, currStructName);
```

#### Busca de Campos
```java
public TS_entry pesquisaCampo(String structId, String campoId) {
    for (TS_entry nodo : lista) {
        if (nodo.getId().equals(campoId) && 
            nodo.getClasse() == ClasseID.CampoStruct && 
            nodo.getEscopo().equals(structId)) {
            return nodo;
        }
    }
    return null;
}
```

## 📊 Estrutura da Tabela de Símbolos

### 1. Organização Hierárquica

```
Tabela de Símbolos
├── Tipos Básicos
│   ├── int
│   ├── float
│   ├── bool
│   ├── string
│   └── struct
├── Structs
│   ├── DATA
│   │   ├── dia (CampoStruct, escopo: DATA)
│   │   ├── mes (CampoStruct, escopo: DATA)
│   │   └── ano (CampoStruct, escopo: DATA)
│   └── ALUNO
│       ├── matricula (CampoStruct, escopo: ALUNO)
│       ├── nome (CampoStruct, escopo: ALUNO)
│       ├── dnasc (CampoStruct, escopo: ALUNO)
│       └── ativo (CampoStruct, escopo: ALUNO)
└── Variáveis Globais
    ├── i (VarGlobal, tipo: int)
    ├── x (VarGlobal, tipo: float)
    ├── b (VarGlobal, tipo: bool)
    ├── alu1 (VarGlobal, tipo: ALUNO)
    ├── alu2 (VarGlobal, tipo: ALUNO)
    ├── d1 (VarGlobal, tipo: DATA)
    └── d2 (VarGlobal, tipo: DATA)
```

### 2. Formato de Saída

```
ident      Classe         Escopo      Tipo
-----------------------------------------------
_erro_    TipoBase    -         null
int       TipoBase    -         null
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
```

## 🎯 Algoritmos Implementados

### 1. Algoritmo de Parsing de Structs

```
ALGORITMO: ParseStruct
ENTRADA: linha contendo "struct NOME {"
SAÍDA: struct inserido na tabela de símbolos

1. Extrair nome do struct da linha
2. Verificar se struct já existe na tabela
3. Se não existe:
   a. Criar entrada TS_entry com classe NomeStruct
   b. Inserir na tabela de símbolos
   c. Definir currStructName = nome do struct
4. Processar campos do struct até encontrar "};"
```

### 2. Algoritmo de Parsing de Campos

```
ALGORITMO: ParseField
ENTRADA: linha contendo "tipo campo;"
SAÍDA: campo inserido na tabela de símbolos

1. Extrair tipo e nome do campo
2. Resolver tipo (básico ou struct)
3. Criar entrada TS_entry com:
   - classe: CampoStruct
   - escopo: currStructName
4. Inserir na tabela de símbolos
```

### 3. Algoritmo de Resolução de Tipos

```
ALGORITMO: ResolveType
ENTRADA: string contendo nome do tipo
SAÍDA: TS_entry representando o tipo

1. Se tipo é básico (int, float, bool, string):
   a. Retornar referência ao tipo básico
2. Senão:
   a. Buscar na tabela de símbolos
   b. Se encontrado e é NomeStruct:
      - Retornar referência ao struct
   c. Senão:
      - Retornar Tp_ERRO
```

## 🔧 Extensões Futuras

### 1. Verificação de Expressões

```java
// Verificar compatibilidade de tipos em atribuições
if (tipoEsquerda != tipoDireita) {
    yyerror("(sem) tipos incompatíveis para atribuição");
}

// Verificar acesso a campos
if (tipo.getClasse() != ClasseID.NomeStruct) {
    yyerror("(sem) acesso a campo em tipo não struct");
}
```

### 2. Verificação de Campos Inexistentes

```java
TS_entry campo = ts.pesquisaCampo(structTipo.getId(), campoNome);
if (campo == null) {
    yyerror("(sem) campo <" + campoNome + "> não existe no struct <" + structTipo.getId() + ">");
}
```

### 3. Verificação de Escopo

```java
// Verificar se variável está no escopo correto
public TS_entry pesquisaComEscopo(String nome, String escopoAtual) {
    // Buscar no escopo atual primeiro
    // Depois buscar em escopos externos
}
```

## 📚 Referências Teóricas

### 1. Compiladores
- **Aho, Sethi, Ullman** - "Compilers: Principles, Techniques, and Tools"
- **Cooper, Torczon** - "Engineering a Compiler"

### 2. Análise Semântica
- **Tradução Dirigida pela Sintaxe**
- **Gramáticas Atribuídas**
- **Tabelas de Símbolos**

### 3. Estruturas de Dados
- **Tabelas Hash**
- **Árvores Binárias**
- **Listas Lineares**

## 🎯 Conclusão

A implementação demonstra compreensão dos conceitos fundamentais de:

1. **Análise Semântica:** Verificação de tipos e declarações
2. **Tabelas de Símbolos:** Estrutura e operações
3. **Structs:** Tipos compostos e escopo
4. **Parsing:** Análise de código fonte
5. **Verificação de Erros:** Detecção de problemas semânticos

A solução fornece uma base sólida para extensões futuras e demonstra aplicação prática dos conceitos teóricos de compiladores.
