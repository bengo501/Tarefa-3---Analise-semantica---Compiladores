    
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
%type <obj> lvalue
%type <obj> TArray
%type <obj> campoList

%%

prog : { currClass = ClasseID.VarGlobal; } dList main ;

dList : decl dList | ;

decl : type TArray IDENT ';' {  TS_entry nodo = ts.pesquisa($3);
                            if (nodo != null) 
                              yyerror("(sem) variavel >" + $3 + "< jah declarada");
                          else {
                              TS_entry tipoFinal = (TS_entry)$2 != null ? (TS_entry)$2 : (TS_entry)$1;
                              ts.insert(new TS_entry($3, tipoFinal, currClass));
                          }
                        }
      | structDecl
      ;

TArray : '[' NUM ']' TArray { $$ = new TS_entry("?", Tp_ARRAY, currClass, $2, (TS_entry)$4); }
       | { $$ = null; }
       ;

SetStructEscopo : STRUCT IDENT '{' 
    {
        currStructName = $2;
        TS_entry structNodo = ts.pesquisa($2);
        if (structNodo != null) {
            yyerror("(sem) struct <" + $2 + "> já declarado");
        } else {
            TS_entry novoStruct = new TS_entry($2, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
            ts.insert(novoStruct);
        }
    }
;

structDecl : SetStructEscopo campoList '}' ';' 
    { 
    }
;

campoList : campoDecl campoList { $$ = null; }
          | { $$ = null; }
          ;

campoDecl : type IDENT ';' {
                TS_entry campo = new TS_entry($2, (TS_entry)$1, ClasseID.CampoStruct, 0, null, currStructName);
                ts.insert(campo);
            }
            ;

              //
              // faria mais sentido reconhecer todos os tipos como ident! 
              // 
type : INT    { $$ = Tp_INT; }
     | DOUBLE  { $$ = Tp_DOUBLE; }
     | FLOAT   { $$ = Tp_FLOAT; }
     | BOOL   { $$ = Tp_BOOL; }
     | STRING { $$ = Tp_STRING; }
     | IDENT  { TS_entry nodo = ts.pesquisa($1);
                if (nodo == null ) 
                   yyerror("(sem) Nome de tipo <" + $1 + "> nao declarado ");
                else 
                    $$ = nodo;
               } 
     ;



main :  VOID MAIN '(' ')' bloco ;

bloco : '{' listacmd '}';

listacmd : listacmd cmd
        |
         ;

cmd :  exp ';' 
      | IF '(' exp ')' cmd   {  if ( ((TS_entry)$3) != Tp_BOOL) 
                                     yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)$3).getTipo());
                             }     
       ;


exp : exp '+' exp { $$ = validaTipo('+', (TS_entry)$1, (TS_entry)$3); }
    | exp '>' exp { $$ = validaTipo('>', (TS_entry)$1, (TS_entry)$3); }
    | exp AND exp { $$ = validaTipo(AND, (TS_entry)$1, (TS_entry)$3); } 
    | NUM         { $$ = Tp_INT; }      
    | '(' exp ')' { $$ = $2; }
    | lvalue   { $$ = $1; }                   
    | lvalue '=' exp  {  $$ = validaTipo(ATRIB, (TS_entry)$1, (TS_entry)$3);  } 
    ;


lvalue : IDENT { 
            TS_entry nodo = ts.pesquisa($1);
            if (nodo == null) {
                yyerror("(sem) var <" + $1 + "> nao declarada"); 
                $$ = Tp_ERRO; 
            } else
                $$ = nodo.getTipo(); // Retorna o TIPO (um TS_entry)
        }

       | lvalue '[' exp ']' { 
            TS_entry tipoLValue = (TS_entry)$1; // $1 é o tipo (ex: Tp_ARRAY)
            TS_entry tipoExp = (TS_entry)$3;    // $3 é o tipo (ex: Tp_INT)

            if (tipoExp != Tp_INT) {
                yyerror("(sem) indexador não é numérico ");
                $$ = Tp_ERRO;
            } else if (tipoLValue != Tp_ARRAY) { // Checagem direta
                yyerror("(sem) elemento não indexado ");
                $$ = Tp_ERRO;
            } else {
                $$ = tipoLValue.getTipoBase(); // Retorna o tipo base
            }
        }

       | lvalue '.' IDENT {
            TS_entry tipoLValue = (TS_entry)$1; // $1 é o tipo (ex: tipo ALUNO, ou tipo DATA)

            if (tipoLValue != null && tipoLValue.getClasse() == ClasseID.NomeStruct) {
                
                // $3 é o nome do campo (String)
                TS_entry campoNodo = ts.pesquisaCampo(tipoLValue.getId(), $3); 
                
                if (campoNodo != null) {
                    $$ = campoNodo.getTipo(); // O tipo deste lvalue é o tipo do campo
                } else {
                    yyerror("(sem) campo >" + $3 + "< nao existe no struct <" + tipoLValue.getId() + ">");
                    $$ = Tp_ERRO;
                }
            } else {
                // O lvalue anterior não era um struct
                yyerror("(sem) elemento nao e struct ou eh nulo");
                $$ = Tp_ERRO;
            }
        }
;
%%

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_FLOAT = new TS_entry("float", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null,  ClasseID.TipoBase);
  public static TS_entry Tp_STRING = new TS_entry("string", null,  ClasseID.TipoBase);
  public static TS_entry Tp_STRUCT = new TS_entry("struct", null,  ClasseID.TipoBase);
  public static TS_entry Tp_ARRAY = new TS_entry("array", null,  ClasseID.TipoBase);
  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private String currEscopo;
  private String currStructName;
  private ClasseID currClass;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }

  public void yyerror (String error) {
    //System.err.println("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
    System.err.printf("Erro (linha: %2d \tMensagem: %s)\n", lexer.getLine(), error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);

    ts = new TabSimb();

    //
    // não me parece que necessitem estar na TS
    // já que criei todas como public static...
    //
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_FLOAT);
    ts.insert(Tp_BOOL);
    ts.insert(Tp_STRING);
    ts.insert(Tp_STRUCT);
    ts.insert(Tp_ARRAY);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");
    

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
        yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

      yyparser.listarTS();

      System.out.print("\nFeito!\n");
    
  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {
       
         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         ((A == Tp_FLOAT && (B == Tp_INT || B == Tp_FLOAT))) ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) ) 
                         return Tp_DOUBLE;     
                    else if ( (A == Tp_FLOAT && (B == Tp_INT || B == Tp_FLOAT)) ||
                                            (B == Tp_FLOAT && (A == Tp_INT || A == Tp_FLOAT)) ) 
                         return Tp_FLOAT;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if ((A == Tp_INT || A == Tp_DOUBLE || A == Tp_FLOAT) && (B == Tp_INT || B == Tp_DOUBLE || B == Tp_FLOAT))
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                      break;

             case AND:
                     if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;
            }

            return Tp_ERRO;
           
     }

