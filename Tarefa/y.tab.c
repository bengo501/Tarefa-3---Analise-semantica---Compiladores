#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 3 "exemploSem.y"
  import java.io.*;
#line 8 "y.tab.c"
#define IDENT 257
#define INT 258
#define DOUBLE 259
#define FLOAT 260
#define BOOL 261
#define NUM 262
#define STRING 263
#define LITERAL 264
#define AND 265
#define VOID 266
#define MAIN 267
#define IF 268
#define STRUCT 269
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    7,    0,    6,    6,    9,    9,    4,    4,   10,   11,
   10,    5,    5,   12,    1,    1,    1,    1,    1,    1,
    8,   13,   14,   14,   15,   15,    2,    2,    2,    2,
    2,    2,    2,    3,    3,    3,
};
short yylen[] = {                                         2,
    0,    3,    2,    0,    4,    1,    4,    0,    6,    3,
    4,    2,    0,    3,    1,    1,    1,    1,    1,    1,
    5,    3,    2,    0,    2,    5,    3,    3,    3,    1,
    3,    1,    3,    1,    4,    3,
};
short yydefred[] = {                                      1,
    0,    0,   20,   15,   16,   17,   18,   19,    0,    0,
    0,    0,    6,    0,    0,    0,    0,    0,    2,    3,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   12,
    0,    0,    5,    0,   14,   11,    0,    7,    0,    9,
   24,   21,    0,    0,   30,    0,   22,    0,    0,    0,
   23,    0,    0,    0,    0,    0,    0,    0,   25,    0,
    0,   36,    0,   31,   29,    0,    0,    0,   35,    0,
   26,
};
short yydgoto[] = {                                       1,
   21,   49,   50,   17,   22,   11,    2,   19,   12,   13,
   14,   23,   42,   43,   51,
};
short yysindex[] = {                                      0,
    0, -218,    0,    0,    0,    0,    0,    0, -242,  -69,
 -217, -218,    0, -184,  -66, -204, -197, -205,    0,    0,
 -194,  -60, -184, -184,  -26,    7,   30,   12,   19,    0,
  -45,  -69,    0,   40,    0,    0,   25,    0,  -37,    0,
    0,    0,  -40,  -27,    0,   42,    0,  -36,  -30,   22,
    0,  -36, -170,  -36,  -35,  -36,  -36,  -36,    0,  -36,
  -32,    0,  -31,    0,    0,  -41, -177,  -29,    0,  -39,
    0,
};
short yyrindex[] = {                                      0,
    0, -176,    0,    0,    0,    0,    0,    0,    0, -168,
    0, -176,    0,  -33,    0,    0,    0,    0,    0,    0,
    0,    0,  -33,  -28,    0,    0,    0,    0,    0,    0,
    0, -168,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -38,    0,    0,    0,    0,    0,  -34,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -24,  -25,  -21,    0,    0,
    0,
};
short yygindex[] = {                                      0,
   24,   -4,    0,   59,   23,   81,    0,    0,    0,    0,
    0,    0,    0,    0,   26,
};
#define YYTABLESIZE 236
short yytable[] = {                                      48,
   48,   58,   34,   48,   34,   64,   32,   58,   32,   70,
   58,   58,   58,   58,   15,   27,   28,   27,   53,   33,
   34,   16,   34,   34,   32,   10,   57,   32,   59,   57,
   57,   57,   57,   27,   28,   10,   27,   33,    3,    4,
    5,    6,    7,   55,    8,   30,   31,   61,   18,   63,
    9,   65,   66,   67,   34,   68,   24,   25,   32,   26,
   69,   27,   28,   52,   29,   33,   32,   27,   28,   34,
   35,   33,    3,    4,    5,    6,    7,   36,    8,   37,
   39,   54,   60,   40,   47,   41,   62,   56,    8,    4,
   38,   13,   20,    0,    0,   71,   10,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   44,   44,    0,    0,
   44,   45,   45,   56,    0,   45,   34,   46,   46,   56,
   32,    0,   56,   56,   56,   56,
};
short yycheck[] = {                                      40,
   40,   43,   41,   40,   43,   41,   41,   43,   43,   41,
   43,   43,   43,   43,  257,   41,   41,   43,   46,   41,
   59,   91,   61,   62,   59,    2,   62,   62,   59,   62,
   62,   62,   62,   59,   59,   12,   62,   59,  257,  258,
  259,  260,  261,   48,  263,   23,   24,   52,  266,   54,
  269,   56,   57,   58,   93,   60,  123,  262,   93,  257,
   93,  267,  257,   91,  125,   59,   93,   93,   93,   40,
   59,   93,  257,  258,  259,  260,  261,   59,  263,  125,
   41,   40,   61,   59,  125,  123,  257,  265,  257,  266,
   32,  125,   12,   -1,   -1,   70,  125,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  257,   -1,   -1,
  257,  262,  262,  265,   -1,  262,  265,  268,  268,  265,
  265,   -1,  265,  265,  265,  265,
};
#define YYFINAL 1
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 269
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'",0,"'+'",0,0,"'.'",0,0,0,0,0,0,0,0,0,0,0,0,"';'",0,"'='",
"'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'['",0,"']'",0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,"'}'",0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,"IDENT","INT","DOUBLE","FLOAT","BOOL","NUM","STRING","LITERAL","AND",
"VOID","MAIN","IF","STRUCT",
};
char *yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList main",
"dList : decl dList",
"dList :",
"decl : type TArray IDENT ';'",
"decl : structDecl",
"TArray : '[' NUM ']' TArray",
"TArray :",
"structDecl : STRUCT IDENT '{' campoList '}' ';'",
"SetStructEscopo : STRUCT IDENT '{'",
"structDecl : SetStructEscopo campoList '}' ';'",
"campoList : campoDecl campoList",
"campoList :",
"campoDecl : type IDENT ';'",
"type : INT",
"type : DOUBLE",
"type : FLOAT",
"type : BOOL",
"type : STRING",
"type : IDENT",
"main : VOID MAIN '(' ')' bloco",
"bloco : '{' listacmd '}'",
"listacmd : listacmd cmd",
"listacmd :",
"cmd : exp ';'",
"cmd : IF '(' exp ')' cmd",
"exp : exp '+' exp",
"exp : exp '>' exp",
"exp : exp AND exp",
"exp : NUM",
"exp : '(' exp ')'",
"exp : lvalue",
"exp : lvalue '=' exp",
"lvalue : IDENT",
"lvalue : IDENT '[' exp ']'",
"lvalue : IDENT '.' IDENT",
};
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 168 "exemploSem.y"

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

      System.out.print("\n\nFeito!\n");
    
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

#line 346 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 28 "exemploSem.y"
{ currClass = ClasseID.VarGlobal; }
break;
case 5:
#line 32 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(yyvsp[-1].sval);
                            if (nodo != null) 
                              yyerror("(sem) variavel >" + yyvsp[-1].sval + "< jah declarada");
                          else {
                              TS_entry tipoFinal = (TS_entry)yyvsp[-2].obj != null ? (TS_entry)yyvsp[-2].obj : (TS_entry)yyvsp[-3].obj;
                              ts.insert(new TS_entry(yyvsp[-1].sval, tipoFinal, currClass));
                          }
                        }
break;
case 7:
#line 43 "exemploSem.y"
{ yyval.obj = new TS_entry("?", Tp_ARRAY, currClass, yyvsp[-2].ival, (TS_entry)yyvsp[0].obj); }
break;
case 8:
#line 44 "exemploSem.y"
{ yyval.obj = null; }
break;
case 9:
#line 47 "exemploSem.y"
{ 
                currStructName = yyvsp[-4].sval;
                TS_entry structNodo = ts.pesquisa(yyvsp[-4].sval);
                if (structNodo != null) {
                    yyerror("(sem) struct <" + yyvsp[-4].sval + "> já declarado");
                } else {
                    TS_entry novoStruct = new TS_entry(yyvsp[-4].sval, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
                    ts.insert(novoStruct);
                }
            }
break;
case 10:
#line 60 "exemploSem.y"
{ 
        currStructName = yyvsp[-1].sval;
        TS_entry structNodo = ts.pesquisa(yyvsp[-1].sval);
        if (structNodo != null) {
            yyerror("(sem) struct <" + yyvsp[-1].sval + "> já declarado");
        } else {
            TS_entry novoStruct = new TS_entry(yyvsp[-1].sval, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
            ts.insert(novoStruct);
        }
    }
break;
case 11:
#line 73 "exemploSem.y"
{ 
    }
break;
case 12:
#line 77 "exemploSem.y"
{ yyval.obj = null; }
break;
case 13:
#line 78 "exemploSem.y"
{ yyval.obj = null; }
break;
case 14:
#line 81 "exemploSem.y"
{
                TS_entry campo = new TS_entry(yyvsp[-1].sval, (TS_entry)yyvsp[-2].obj, ClasseID.CampoStruct, 0, null, currStructName);
                ts.insert(campo);
            }
break;
case 15:
#line 90 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 16:
#line 91 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 17:
#line 92 "exemploSem.y"
{ yyval.obj = Tp_FLOAT; }
break;
case 18:
#line 93 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 19:
#line 94 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 20:
#line 95 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(yyvsp[0].sval);
                if (nodo == null ) 
                   yyerror("(sem) Nome de tipo <" + yyvsp[0].sval + "> nao declarado ");
                else 
                    yyval.obj = nodo;
               }
break;
case 26:
#line 114 "exemploSem.y"
{  if ( ((TS_entry)yyvsp[-2].obj) != Tp_BOOL) 
                                     yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)yyvsp[-2].obj).getTipo());
                             }
break;
case 27:
#line 120 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)yyvsp[-2].obj, (TS_entry)yyvsp[0].obj); }
break;
case 28:
#line 121 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)yyvsp[-2].obj, (TS_entry)yyvsp[0].obj); }
break;
case 29:
#line 122 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)yyvsp[-2].obj, (TS_entry)yyvsp[0].obj); }
break;
case 30:
#line 123 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 31:
#line 124 "exemploSem.y"
{ yyval.obj = yyvsp[-1].obj; }
break;
case 32:
#line 125 "exemploSem.y"
{ yyval.obj = yyvsp[0].obj; }
break;
case 33:
#line 126 "exemploSem.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)yyvsp[-2].obj, (TS_entry)yyvsp[0].obj);  }
break;
case 34:
#line 130 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(yyvsp[0].sval);
                    if (nodo == null) {
                       yyerror("(sem) var <" + yyvsp[0].sval + "> nao declarada"); 
                       yyval.obj = Tp_ERRO;    
                       }           
                    else
                        yyval.obj = nodo.getTipo();
                  }
break;
case 35:
#line 138 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(yyvsp[-3].sval);
                              if (nodo == null) {
                                 yyerror("(sem) var <" + yyvsp[-3].sval + "> nao declarada");
                                 yyval.obj = Tp_ERRO;
                              } else if ((TS_entry)yyvsp[-1].obj != Tp_INT) {
                                 yyerror("(sem) indexador não é numérico ");
                                 yyval.obj = Tp_ERRO;
                              } else if (nodo.getTipo() != Tp_ARRAY) {
                                 yyerror("(sem) elemento não indexado ");
                                 yyval.obj = Tp_ERRO;
                              } else {
                                 yyval.obj = nodo.getTipoBase();
                              }
                         }
break;
case 36:
#line 152 "exemploSem.y"
{ TS_entry structNodo = ts.pesquisa(yyvsp[-2].sval);
                             if (structNodo != null && structNodo.getTipo() != null && 
                                 structNodo.getTipo().getClasse() == ClasseID.NomeStruct) {
                                 TS_entry campoNodo = ts.pesquisaCampo(structNodo.getTipo().getId(), yyvsp[0].sval);
                                 if (campoNodo != null) {
                                     yyval.obj = campoNodo.getTipo();
                                 } else {
                                     yyerror("(sem) campo >" + yyvsp[0].sval + "< nao existe no struct <" + structNodo.getTipo().getId() + ">");
                                     yyval.obj = Tp_ERRO;
                                 }
                             } else {
                                 yyerror("(sem) variavel >" + yyvsp[-2].sval + "< nao declarada ou nao e struct");
                                 yyval.obj = Tp_ERRO;
                             }
                         }
break;
#line 664 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
