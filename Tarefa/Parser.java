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






//#line 2 "exemploSem.y"

  import java.io.*;
//#line 20 "Parser.java"




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
    7,    0,    6,    6,    9,    9,    4,    4,   10,    5,
    5,   11,    1,    1,    1,    1,    1,    1,    8,   12,
   13,   13,   14,   14,    2,    2,    2,    2,    2,    2,
    2,    3,    3,    3,
};
final static short yylen[] = {                            2,
    0,    3,    2,    0,    4,    1,    4,    0,    6,    2,
    0,    3,    1,    1,    1,    1,    1,    1,    5,    3,
    2,    0,    2,    5,    3,    3,    3,    1,    3,    1,
    3,    1,    4,    3,
};
final static short yydefred[] = {                         1,
    0,    0,   18,   13,   14,   15,   16,   17,    0,    0,
    0,    0,    6,    0,    0,    0,    0,    2,    3,    0,
    0,    0,    0,    0,    0,    0,    0,    5,    0,    0,
    0,   10,    7,    0,   12,    9,   22,   19,    0,    0,
   28,    0,   20,    0,    0,    0,   21,    0,    0,    0,
    0,    0,    0,    0,   23,    0,    0,   34,    0,   29,
   27,    0,    0,    0,   33,    0,   24,
};
final static short yydgoto[] = {                          1,
   10,   45,   46,   16,   25,   11,    2,   18,   12,   13,
   26,   38,   39,   47,
};
final static short yysindex[] = {                         0,
    0, -218,    0,    0,    0,    0,    0,    0, -242,  -69,
 -240, -218,    0,  -87, -216, -210, -214,    0,    0, -184,
  -44,    3,   23, -192,  -59, -184,  -69,    0,   26,   11,
   12,    0,    0,  -45,    0,    0,    0,    0,  -40,  -27,
    0,   40,    0,  -36,  -30,   20,    0,  -36, -175,  -36,
  -35,  -36,  -36,  -36,    0,  -36,  -32,    0,  -31,    0,
    0,  -41, -182,  -29,    0,  -39,    0,
};
final static short yyrindex[] = {                         0,
    0, -180,    0,    0,    0,    0,    0,    0,    0, -173,
    0, -180,    0,    0,    0,    0,    0,    0,    0,  -37,
    0,    0,    0,    0,    0,  -37, -173,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -38,
    0,    0,    0,    0,    0,  -34,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -24,  -25,  -21,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   24,    4,    0,   60,   63,   78,    0,    0,    0,    0,
    0,    0,    0,   25,
};
final static int YYTABLESIZE=236;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         44,
   44,   54,   32,   44,   32,   60,   30,   54,   30,   66,
   54,   54,   54,   54,   14,   25,   26,   25,   49,   31,
   32,   15,   32,   32,   30,   17,   53,   30,   55,   53,
   53,   53,   53,   25,   26,   20,   25,   31,    3,    4,
    5,    6,    7,   24,    8,   21,   22,   51,   27,   24,
    9,   57,   23,   59,   32,   61,   62,   63,   30,   64,
   65,   28,   29,   48,   30,   31,   34,   25,   26,   35,
   36,   31,    3,    4,    5,    6,    7,   37,    8,   50,
   56,   58,   52,    8,   43,    4,   33,   11,   32,   19,
   67,    0,    0,    0,    0,    0,    0,    0,    0,    0,
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
    0,    0,    0,    0,    0,    0,   40,   40,    0,    0,
   40,   41,   41,   52,    0,   41,   32,   42,   42,   52,
   30,    0,   52,   52,   52,   52,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   40,   43,   41,   40,   43,   41,   41,   43,   43,   41,
   43,   43,   43,   43,  257,   41,   41,   43,   46,   41,
   59,   91,   61,   62,   59,  266,   62,   62,   59,   62,
   62,   62,   62,   59,   59,  123,   62,   59,  257,  258,
  259,  260,  261,   20,  263,  262,  257,   44,   93,   26,
  269,   48,  267,   50,   93,   52,   53,   54,   93,   56,
   93,   59,   40,   91,  257,  125,   41,   93,   93,   59,
   59,   93,  257,  258,  259,  260,  261,  123,  263,   40,
   61,  257,  265,  257,  125,  266,   27,  125,   26,   12,
   66,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
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
"structDecl : STRUCT IDENT '{' campoList '}' ';'",
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

//#line 149 "exemploSem.y"


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

//#line 410 "Parser.java"
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
//#line 47 "exemploSem.y"
{ 
                currStructName = val_peek(4).sval;
                TS_entry structNodo = ts.pesquisa(val_peek(4).sval);
                if (structNodo != null) {
                    yyerror("(sem) struct <" + val_peek(4).sval + "> já declarado");
                } else {
                    TS_entry novoStruct = new TS_entry(val_peek(4).sval, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
                    ts.insert(novoStruct);
                }
            }
break;
case 10:
//#line 59 "exemploSem.y"
{ yyval.obj = null; }
break;
case 11:
//#line 60 "exemploSem.y"
{ yyval.obj = null; }
break;
case 12:
//#line 63 "exemploSem.y"
{
                TS_entry campo = new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, ClasseID.CampoStruct, 0, null, currStructName);
                ts.insert(campo);
            }
break;
case 13:
//#line 72 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 14:
//#line 73 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 15:
//#line 74 "exemploSem.y"
{ yyval.obj = Tp_FLOAT; }
break;
case 16:
//#line 75 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 17:
//#line 76 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 18:
//#line 77 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                if (nodo == null ) 
                   yyerror("(sem) Nome de tipo <" + val_peek(0).sval + "> nao declarado ");
                else 
                    yyval.obj = nodo;
               }
break;
case 24:
//#line 96 "exemploSem.y"
{  if ( ((TS_entry)val_peek(2).obj) != Tp_BOOL) 
                                     yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)val_peek(2).obj).getTipo());
                             }
break;
case 25:
//#line 102 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 26:
//#line 103 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 27:
//#line 104 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 28:
//#line 105 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 29:
//#line 106 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 30:
//#line 107 "exemploSem.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 31:
//#line 108 "exemploSem.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
case 32:
//#line 112 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                    if (nodo == null) {
                       yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada"); 
                       yyval.obj = Tp_ERRO;    
                       }           
                    else
                        yyval.obj = nodo.getTipo();
                  }
break;
case 33:
//#line 120 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(3).sval);
                              if (nodo == null) {
                                 yyerror("(sem) var <" + val_peek(3).sval + "> nao declarada");
                                 yyval.obj = Tp_ERRO;
                              } else if ((TS_entry)val_peek(1).obj != Tp_INT) {
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
case 34:
//#line 134 "exemploSem.y"
{ TS_entry structNodo = ts.pesquisa(val_peek(2).sval);
                             if (structNodo != null && structNodo.getTipo() != null && 
                                 structNodo.getTipo().getClasse() == ClasseID.NomeStruct) {
                                 TS_entry campoNodo = ts.pesquisaCampo(structNodo.getTipo().getId(), val_peek(0).sval);
                                 if (campoNodo != null) {
                                     yyval.obj = campoNodo.getTipo();
                                 } else {
                                     yyerror("(sem) campo >" + val_peek(0).sval + "< nao existe no struct <" + structNodo.getTipo().getId() + ">");
                                     yyval.obj = Tp_ERRO;
                                 }
                             } else {
                                 yyerror("(sem) variavel >" + val_peek(2).sval + "< nao declarada ou nao e struct");
                                 yyval.obj = Tp_ERRO;
                             }
                         }
break;
//#line 719 "Parser.java"
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
