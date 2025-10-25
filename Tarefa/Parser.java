//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploSem.y"
  import java.io.*;
//#line 19 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENT=257;
public final static short INT=258;
public final static short DOUBLE=259;
public final static short FLOAT=260;
public final static short BOOL=261;
public final static short NUM=262;
public final static short STRING=263;
public final static short LITERAL=264;
public final static short AND=265;
public final static short VOID=266;
public final static short MAIN=267;
public final static short IF=268;
public final static short STRUCT=269;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    7,    0,    6,    6,    9,    9,    4,    4,   11,   10,
    5,    5,   12,    1,    1,    1,    1,    1,    1,    8,
   13,   14,   14,   15,   15,    2,    2,    2,    2,    2,
    2,    2,    3,    3,    3,
};
final static short yylen[] = {                            2,
    0,    3,    2,    0,    4,    1,    4,    0,    3,    4,
    2,    0,    3,    1,    1,    1,    1,    1,    1,    5,
    3,    2,    0,    2,    5,    3,    3,    3,    1,    3,
    1,    3,    1,    4,    3,
};
final static short yydefred[] = {                         1,
    0,    0,   19,   14,   15,   16,   17,   18,    0,    0,
    0,    0,    6,    0,    0,    0,    0,    0,    2,    3,
    0,    0,    0,    9,    0,    0,    0,    0,    0,   11,
    0,    5,    0,   13,   10,    7,    0,   23,   20,    0,
   33,   29,    0,   21,    0,    0,    0,   22,    0,    0,
    0,    0,    0,   24,    0,    0,    0,    0,   30,   28,
    0,    0,    0,    0,   35,    0,   34,   25,
};
final static short yydgoto[] = {                          1,
   10,   46,   47,   17,   22,   11,    2,   19,   12,   13,
   14,   23,   39,   40,   48,
};
final static short yysindex[] = {                         0,
    0, -219,    0,    0,    0,    0,    0,    0, -240,  -72,
 -246, -219,    0, -186, -100, -237, -220, -224,    0,    0,
 -212,  -79, -186,    0,  -46,  -11,   11,   -6,   -5,    0,
  -72,    0,   16,    0,    0,    0,  -59,    0,    0,  -40,
    0,    0,   25,    0,  -36,  -33,  -12,    0,  -36,  -35,
  -36,  -36,  -36,    0,  -36,  -36, -189,  -34,    0,    0,
  -41, -196,  -31,  -32,    0,  -39,    0,    0,
};
final static short yyrindex[] = {                         0,
    0, -190,    0,    0,    0,    0,    0,    0,    0, -179,
    0, -190,    0,  -45,    0,    0,    0,    0,    0,    0,
    0,    0,  -45,    0,    0,    0,    0,    0,    0,    0,
 -179,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -38,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -26,  -27,  -23,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   -1,    7,    0,   50,   59,   71,    0,    0,    0,    0,
    0,    0,    0,    0,   18,
};
final static int YYTABLESIZE=234;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         45,
   45,   53,   31,   45,   31,   59,   66,   53,   53,   53,
   53,   53,   21,   26,   27,   26,   15,   32,   16,   18,
   31,   21,   24,   31,   25,   54,   52,   52,   52,   52,
   52,   26,   27,   57,   26,   32,   26,    3,    4,    5,
    6,    7,   27,    8,   28,   29,   31,   32,   55,    9,
   33,   50,   34,   35,   31,   58,   37,   60,   61,   62,
   67,   63,   64,   38,   49,   26,   27,   65,   51,   32,
    3,    4,    5,    6,    7,    4,    8,    8,   56,   12,
   36,   30,   20,   68,   44,    0,    0,    0,    0,    0,
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
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   41,   41,    0,    0,
   41,   42,   42,   51,    0,   42,   31,   43,   43,   51,
   51,   51,   51,   51,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   40,   43,   41,   40,   43,   41,   41,   43,   43,   43,
   43,   43,   14,   41,   41,   43,  257,   41,   91,  266,
   59,   23,  123,   62,  262,   59,   62,   62,   62,   62,
   62,   59,   59,   46,   62,   59,  257,  257,  258,  259,
  260,  261,  267,  263,  257,  125,   93,   59,   61,  269,
   40,   45,   59,   59,   93,   49,   41,   51,   52,   53,
   93,   55,   56,  123,   40,   93,   93,  257,  265,   93,
  257,  258,  259,  260,  261,  266,  263,  257,   91,  125,
   31,   23,   12,   66,  125,   -1,   -1,   -1,   -1,   -1,
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
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  257,   -1,   -1,
  257,  262,  262,  265,   -1,  262,  265,  268,  268,  265,
  265,  265,  265,  265,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=269;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,"'+'",null,
null,"'.'",null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENT","INT","DOUBLE","FLOAT","BOOL",
"NUM","STRING","LITERAL","AND","VOID","MAIN","IF","STRUCT",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList main",
"dList : decl dList",
"dList :",
"decl : type TArray IDENT ';'",
"decl : structDecl",
"TArray : '[' NUM ']' TArray",
"TArray :",
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
"lvalue : lvalue '[' exp ']'",
"lvalue : lvalue '.' IDENT",
};

//#line 164 "exemploSem.y"

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

//#line 406 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 28 "exemploSem.y"
{ currClass = ClasseID.VarGlobal; }
break;
case 5:
//#line 32 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
                            if (nodo != null) 
                              yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          else {
                              TS_entry tipoFinal = (TS_entry)val_peek(2).obj != null ? (TS_entry)val_peek(2).obj : (TS_entry)val_peek(3).obj;
                              ts.insert(new TS_entry(val_peek(1).sval, tipoFinal, currClass));
                          }
                        }
break;
case 7:
//#line 43 "exemploSem.y"
{ yyval.obj = new TS_entry("?", Tp_ARRAY, currClass, val_peek(2).ival, (TS_entry)val_peek(0).obj); }
break;
case 8:
//#line 44 "exemploSem.y"
{ yyval.obj = null; }
break;
case 9:
//#line 48 "exemploSem.y"
{
        currStructName = val_peek(1).sval;
        TS_entry structNodo = ts.pesquisa(val_peek(1).sval);
        if (structNodo != null) {
            yyerror("(sem) struct <" + val_peek(1).sval + "> já declarado");
        } else {
            TS_entry novoStruct = new TS_entry(val_peek(1).sval, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
            ts.insert(novoStruct);
        }
    }
break;
case 10:
//#line 61 "exemploSem.y"
{ 
    }
break;
case 11:
//#line 65 "exemploSem.y"
{ yyval.obj = null; }
break;
case 12:
//#line 66 "exemploSem.y"
{ yyval.obj = null; }
break;
case 13:
//#line 69 "exemploSem.y"
{
                TS_entry campo = new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, ClasseID.CampoStruct, 0, null, currStructName);
                ts.insert(campo);
            }
break;
case 14:
//#line 78 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 15:
//#line 79 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 16:
//#line 80 "exemploSem.y"
{ yyval.obj = Tp_FLOAT; }
break;
case 17:
//#line 81 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 18:
//#line 82 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 19:
//#line 83 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                if (nodo == null ) 
                   yyerror("(sem) Nome de tipo <" + val_peek(0).sval + "> nao declarado ");
                else 
                    yyval.obj = nodo;
               }
break;
case 25:
//#line 102 "exemploSem.y"
{  if ( ((TS_entry)val_peek(2).obj) != Tp_BOOL) 
                                     yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)val_peek(2).obj).getTipo());
                             }
break;
case 26:
//#line 108 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 27:
//#line 109 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 28:
//#line 110 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 29:
//#line 111 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 30:
//#line 112 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 31:
//#line 113 "exemploSem.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 32:
//#line 114 "exemploSem.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
case 33:
//#line 118 "exemploSem.y"
{ 
            TS_entry nodo = ts.pesquisa(val_peek(0).sval);
            if (nodo == null) {
                yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada"); 
                yyval.obj = Tp_ERRO; 
            } else
                yyval.obj = nodo.getTipo(); /* Retorna o TIPO (um TS_entry)*/
        }
break;
case 34:
//#line 127 "exemploSem.y"
{ 
            TS_entry tipoLValue = (TS_entry)val_peek(3).obj; /* $1 é o tipo (ex: Tp_ARRAY)*/
            TS_entry tipoExp = (TS_entry)val_peek(1).obj;    /* $3 é o tipo (ex: Tp_INT)*/

            if (tipoExp != Tp_INT) {
                yyerror("(sem) indexador não é numérico ");
                yyval.obj = Tp_ERRO;
            } else if (tipoLValue != Tp_ARRAY) { /* Checagem direta*/
                yyerror("(sem) elemento não indexado ");
                yyval.obj = Tp_ERRO;
            } else {
                yyval.obj = tipoLValue.getTipoBase(); /* Retorna o tipo base*/
            }
        }
break;
case 35:
//#line 142 "exemploSem.y"
{
            TS_entry tipoLValue = (TS_entry)val_peek(2).obj; /* $1 é o tipo (ex: tipo ALUNO, ou tipo DATA)*/

            if (tipoLValue != null && tipoLValue.getClasse() == ClasseID.NomeStruct) {
                
                /* $3 é o nome do campo (String)*/
                TS_entry campoNodo = ts.pesquisaCampo(tipoLValue.getId(), val_peek(0).sval); 
                
                if (campoNodo != null) {
                    yyval.obj = campoNodo.getTipo(); /* O tipo deste lvalue é o tipo do campo*/
                } else {
                    yyerror("(sem) campo >" + val_peek(0).sval + "< nao existe no struct <" + tipoLValue.getId() + ">");
                    yyval.obj = Tp_ERRO;
                }
            } else {
                /* O lvalue anterior não era um struct*/
                yyerror("(sem) elemento nao e struct ou eh nulo");
                yyval.obj = Tp_ERRO;
            }
        }
break;
//#line 725 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
