    
%{
  import java.io.*;
%}


%token IDENT, INT, DOUBLE, BOOL, NUM, STRING
%token LITERAL, AND, VOID, MAIN, IF
%token STRUCT
%token FLOAT

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

dList : decl dList | structDecl dList | ;

decl : type  { currentType = (TS_entry)$1; } TArray Lid ';'
      ;

Lid : Lid  ',' id 
    | id  
    ;

id : IDENT   { TS_entry nodo = ts.pesquisa($1);
                if (nodo != null) 
                    yyerror("(sem) variavel >" + $1 + "< jah declarada");
                else ts.insert(new TS_entry($1, currentType, currClass)); 
              }
    
    ;

TArray : '[' NUM ']'  TArray { currentType = new TS_entry("?", Tp_ARRAY, 
                                                   currClass, $2, currentType); }
          
       |
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
                if (nodo == null || nodo.getClasse() != ClasseID.NomeStruct) {
                   yyerror("(sem) tipo <" + $1 + "> nao declarado");
                   $$ = Tp_ERRO;
                } else {
                   $$ = nodo;
                }
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
    | IDENT       { TS_entry nodo = ts.pesquisa($1);
                    if (nodo == null) {
                       yyerror("(sem) var <" + $1 + "> nao declarada"); 
                       $$ = Tp_ERRO;    
                       }           
                    else
                        $$ = nodo.getTipo();
                  }                   
     | exp '=' exp  {  $$ = validaTipo(ATRIB, (TS_entry)$1, (TS_entry)$3);  } 
     | exp '[' exp ']'  {  if ((TS_entry)$3 != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)$1).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  $$ = ((TS_entry)$1).getTipoBase();
                         } 
     | exp '.' IDENT    {  TS_entry structTipo = ((TS_entry)$1).getTipo();
                           if (structTipo == null || structTipo.getClasse() != ClasseID.NomeStruct) {
                               yyerror("(sem) acesso a campo em tipo não struct");
                               $$ = Tp_ERRO;
                           } else {
                               TS_entry campo = ts.pesquisaCampo(structTipo.getId(), $3);
                               if (campo == null) {
                                   yyerror("(sem) campo <" + $3 + "> não existe no struct <" + structTipo.getId() + ">");
                                   $$ = Tp_ERRO;
                               } else {
                                   $$ = campo.getTipo();
                               }
                           }
                         }
    ;

structDecl : STRUCT IDENT '{' campoList '}' ';'
             {  TS_entry structNodo = ts.pesquisa($2);
                if (structNodo != null) {
                    yyerror("(sem) struct <" + $2 + "> já declarado");
                } else {
                    currStructName = $2;
                    TS_entry novoStruct = new TS_entry($2, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
                    ts.insert(novoStruct);
                }
             }
           ;

campoList : campoList campoDecl | campoDecl | ;

campoDecl : type IDENT ';'
            {  TS_entry structNodo = ts.pesquisa(currStructName);
               if (structNodo != null) {
                   TS_entry campo = new TS_entry($2, (TS_entry)$1, ClasseID.CampoStruct, 0, null, currStructName);
                   ts.insert(campo);
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

  public static TS_entry Tp_ARRAY = new TS_entry("array", null,  ClasseID.TipoBase);
  public static TS_entry Tp_STRUCT = new TS_entry("struct", null,  ClasseID.TipoBase);

  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private String currEscopo;
  private String currStructName;

  private ClasseID currClass;
  private TS_entry currentType;

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
    System.err.printf("Erro (linha: %2d) \tMensagem: %s\n", lexer.getLine(), error);
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
    ts.insert(Tp_ARRAY);
    ts.insert(Tp_STRUCT);
    

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

      System.out.print("\n\nFeito!\n");
    
  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {
       
         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
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
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
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

