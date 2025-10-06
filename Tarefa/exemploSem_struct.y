%{
  import java.io.*;
%}

%token IDENT, INT, DOUBLE, FLOAT, BOOL, NUM, STRING
%token LITERAL, AND, VOID, MAIN, IF
%token STRUCT

%right '='
%nonassoc '>'
%left '+'
%left AND
%left '['  
%left '.'

%type <sval> IDENT
%type <ival> NUM
%type <obj> type
%type <obj> exp

%%

prog : { currClass = ClasseID.VarGlobal; } dList main ;

dList : decl dList | ;

decl : type  { currentType = (TS_entry)$1; } TArray Lid ';'
      | structDecl
      ;

structDecl : STRUCT IDENT { 
                currStructName = $2;
                TS_entry structNodo = ts.pesquisa($2);
                if (structNodo != null) {
                    yyerror("(sem) struct <" + $2 + "> já declarado");
                } else {
                    TS_entry novoStruct = new TS_entry($2, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
                    ts.insert(novoStruct);
                }
            } '{' campoList '}' ';'
      ;

campoList : campoDecl campoList | ;

campoDecl : type { currentType = (TS_entry)$1; } IDENT ';' {
                TS_entry campo = new TS_entry($3, currentType, ClasseID.CampoStruct, 0, null, currStructName);
                ts.insert(campo);
            }
      ;

TArray : '[' NUM ']' { $$ = new TS_entry("array", currentType, ClasseID.TipoBase, $2, currentType, "-"); }
       | { $$ = currentType; }
       ;

Lid : IDENT { 
         TS_entry nodo = ts.pesquisa($1);
         if (nodo != null) {
             yyerror("(sem) variavel >" + $1 + "< jah declarada");
         } else {
             ts.insert(new TS_entry($1, currentType, currClass));
         }
     }
   | IDENT ',' Lid
   ;

type : INT { $$ = Tp_INT; }
     | DOUBLE { $$ = Tp_DOUBLE; }
     | FLOAT { $$ = Tp_FLOAT; }
     | BOOL { $$ = Tp_BOOL; }
     | STRING { $$ = Tp_STRING; }
     | IDENT { 
         TS_entry nodo = ts.pesquisa($1);
         if (nodo != null && nodo.getClasse() == ClasseID.NomeStruct) {
             $$ = nodo;
         } else {
             yyerror("(sem) tipo <" + $1 + "> nao declarado");
             $$ = Tp_ERRO;
         }
       }
     ;

main : VOID MAIN '(' ')' bloco ;

bloco : '{' listaCmd '}' ;

listaCmd : cmd listaCmd | ;

cmd : exp ';'
    | IF '(' exp ')' cmd
    ;

exp : NUM { $$ = new TS_entry("num", Tp_INT, ClasseID.TipoBase); }
    | IDENT { 
        TS_entry nodo = ts.pesquisa($1);
        if (nodo != null) {
            $$ = nodo;
        } else {
            yyerror("(sem) variavel >" + $1 + "< nao declarada");
            $$ = Tp_ERRO;
        }
      }
    | IDENT '.' IDENT {
        TS_entry structNodo = ts.pesquisa($1);
        if (structNodo != null && structNodo.getClasse() == ClasseID.VarGlobal) {
            TS_entry campoNodo = ts.pesquisaCampo(structNodo.getTipo().getId(), $3);
            if (campoNodo != null) {
                $$ = campoNodo;
            } else {
                yyerror("(sem) campo >" + $3 + "< nao existe no struct <" + structNodo.getTipo().getId() + ">");
                $$ = Tp_ERRO;
            }
        } else {
            yyerror("(sem) variavel >" + $1 + "< nao declarada ou nao e struct");
            $$ = Tp_ERRO;
        }
      }
    | exp '+' exp { $$ = new TS_entry("+", Tp_INT, ClasseID.TipoBase); }
    | exp '>' exp { $$ = new TS_entry(">", Tp_BOOL, ClasseID.TipoBase); }
    | exp AND exp { $$ = new TS_entry("&&", Tp_BOOL, ClasseID.TipoBase); }
    | exp '=' exp { $$ = new TS_entry("=", Tp_INT, ClasseID.TipoBase); }
    | '(' exp ')' { $$ = $2; }
    ;

%%

static TS_entry Tp_INT = new TS_entry("int", null, ClasseID.TipoBase);
static TS_entry Tp_DOUBLE = new TS_entry("double", null, ClasseID.TipoBase);
static TS_entry Tp_FLOAT = new TS_entry("float", null, ClasseID.TipoBase);
static TS_entry Tp_BOOL = new TS_entry("bool", null, ClasseID.TipoBase);
static TS_entry Tp_STRING = new TS_entry("string", null, ClasseID.TipoBase);
static TS_entry Tp_ARRAY = new TS_entry("array", null, ClasseID.TipoBase);
static TS_entry Tp_STRUCT = new TS_entry("struct", null, ClasseID.TipoBase);
static TS_entry Tp_ERRO = new TS_entry("_erro_", null, ClasseID.TipoBase);

static TabSimb ts = new TabSimb();
static ClasseID currClass;
static TS_entry currentType;
static String currStructName;

static void yyerror(String s) {
    System.err.println("Erro (linha: " + yyline + ") \tMensagem: " + s);
}

static void listarTS() {
    ts.listar();
}

public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");

    Parser yyparser = new Parser(new Yylex(new FileReader(args[0]), null));
    yyparser.yyparse();

    listarTS();
    System.out.print("\n\nFeito!\n");
}
