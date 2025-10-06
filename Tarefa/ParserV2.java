import java.io.*;

public class ParserV2 {
    private Yylex lexer;
    private TabSimb ts;

    public static TS_entry Tp_INT = new TS_entry("int", null, ClasseID.TipoBase);
    public static TS_entry Tp_DOUBLE = new TS_entry("double", null, ClasseID.TipoBase);
    public static TS_entry Tp_FLOAT = new TS_entry("float", null, ClasseID.TipoBase);
    public static TS_entry Tp_BOOL = new TS_entry("bool", null, ClasseID.TipoBase);
    public static TS_entry Tp_STRING = new TS_entry("string", null, ClasseID.TipoBase);
    public static TS_entry Tp_ARRAY = new TS_entry("array", null, ClasseID.TipoBase);
    public static TS_entry Tp_STRUCT = new TS_entry("struct", null, ClasseID.TipoBase);
    public static TS_entry Tp_ERRO = new TS_entry("_erro_", null, ClasseID.TipoBase);

    public static final int AND = 1000;
    public static final int ARRAY = 1500;
    public static final int ATRIB = 1600;

    // Tokens
    public static final int IDENT = 2000;
    public static final int INT = 2001;
    public static final int DOUBLE = 2002;
    public static final int FLOAT = 2003;
    public static final int BOOL = 2004;
    public static final int STRING = 2005;
    public static final int NUM = 2006;
    public static final int LITERAL = 2007;
    public static final int VOID = 2008;
    public static final int MAIN = 2009;
    public static final int IF = 2010;
    public static final int STRUCT = 2011;

    private String currEscopo;
    private String currStructName;
    private ClasseID currClass;
    private TS_entry currentType;
    
    public ParserVal yylval;

    public ParserV2(Reader r) {
        lexer = new Yylex(r, this);
        ts = new TabSimb();

        ts.insert(Tp_ERRO);
        ts.insert(Tp_INT);
        ts.insert(Tp_DOUBLE);
        ts.insert(Tp_FLOAT);
        ts.insert(Tp_BOOL);
        ts.insert(Tp_STRING);
        ts.insert(Tp_ARRAY);
        ts.insert(Tp_STRUCT);
    }

    public void yyerror(String error) {
        System.err.printf("Erro (linha: %2d) \tMensagem: %s\n", lexer.getLine(), error);
    }

    public void listarTS() { 
        ts.listar();
    }

    public void setDebug(boolean debug) {
        // Implementação básica
    }

    public void yyparse() {
        try {
            currClass = ClasseID.VarGlobal;
            parseProgram();
        } catch (Exception e) {
            System.err.println("Erro durante parsing: " + e.getMessage());
        }
    }

    private void parseProgram() throws IOException {
        // Primeiro, processar todas as declarações de struct
        parseStructDeclarations();
        // Depois, processar declarações de variáveis
        parseVarDeclarations();
        // Por fim, processar a função main
        parseMain();
    }

    private void parseStructDeclarations() throws IOException {
        int token = lexer.yylex();
        while (token != Yylex.YYEOF && token != 0) {
            if (token == STRUCT) {
                parseStructDecl();
            } else if (token == VOID) {
                // Chegou na função main, voltar o token
                lexer.yyreset();
                break;
            } else if (isTypeToken(token)) {
                // É uma declaração de variável, voltar o token
                lexer.yyreset();
                break;
            }
            token = lexer.yylex();
        }
    }

    private void parseVarDeclarations() throws IOException {
        int token = lexer.yylex();
        while (token != Yylex.YYEOF && token != 0) {
            if (isTypeToken(token)) {
                parseVarDecl(token);
            } else if (token == VOID) {
                // Chegou na função main, voltar o token
                lexer.yyreset();
                break;
            }
            token = lexer.yylex();
        }
    }

    private void parseStructDecl() throws IOException {
        int token = lexer.yylex();
        if (token == IDENT) {
            String structName = lexer.yytext();
            TS_entry structNodo = ts.pesquisa(structName);
            if (structNodo != null) {
                yyerror("(sem) struct <" + structName + "> já declarado");
            } else {
                currStructName = structName;
                TS_entry novoStruct = new TS_entry(structName, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
                ts.insert(novoStruct);
            }

            token = lexer.yylex(); // '{'
            if (token == '{') {
                parseCampoList();
                token = lexer.yylex(); // '}'
                token = lexer.yylex(); // ';'
            }
        }
    }

    private void parseCampoList() throws IOException {
        int token = lexer.yylex();
        while (token != '}') {
            if (isTypeToken(token)) {
                parseCampoDecl(token);
            }
            token = lexer.yylex();
        }
    }

    private void parseCampoDecl(int typeToken) throws IOException {
        TS_entry tipo = getTypeFromToken(typeToken);
        int token = lexer.yylex();
        if (token == IDENT) {
            String campoName = lexer.yytext();
            TS_entry campo = new TS_entry(campoName, tipo, ClasseID.CampoStruct, 0, null, currStructName);
            ts.insert(campo);
            token = lexer.yylex(); // ';'
        }
    }

    private void parseVarDecl(int typeToken) throws IOException {
        currentType = getTypeFromToken(typeToken);
        int token = lexer.yylex();
        if (token == IDENT) {
            String varName = lexer.yytext();
            TS_entry nodo = ts.pesquisa(varName);
            if (nodo != null) {
                yyerror("(sem) variavel >" + varName + "< jah declarada");
            } else {
                ts.insert(new TS_entry(varName, currentType, currClass));
            }
            token = lexer.yylex(); // ';'
        }
    }

    private void parseMain() throws IOException {
        int token = lexer.yylex();
        if (token == VOID) {
            token = lexer.yylex();
            if (token == MAIN) {
                token = lexer.yylex(); // '('
                token = lexer.yylex(); // ')'
                parseBloco();
            }
        }
    }

    private void parseBloco() throws IOException {
        int token = lexer.yylex(); // '{'
        if (token == '{') {
            parseListaCmd();
            token = lexer.yylex(); // '}'
        }
    }

    private void parseListaCmd() throws IOException {
        int token = lexer.yylex();
        while (token != '}') {
            if (token == IF) {
                parseIfCmd();
            } else {
                parseExp();
                token = lexer.yylex(); // ';'
            }
            token = lexer.yylex();
        }
    }

    private void parseIfCmd() throws IOException {
        int token = lexer.yylex(); // '('
        TS_entry expTipo = parseExp();
        if (expTipo != Tp_BOOL) {
            yyerror("(sem) expressão (if) deve ser lógica " + expTipo.getTipoStr());
        }
        token = lexer.yylex(); // ')'
        parseCmd();
    }

    private void parseCmd() throws IOException {
        int token = lexer.yylex();
        if (token == IF) {
            parseIfCmd();
        } else {
            parseExp();
            token = lexer.yylex(); // ';'
        }
    }

    private TS_entry parseExp() throws IOException {
        int token = lexer.yylex();
        if (token == NUM) {
            return Tp_INT;
        } else if (token == IDENT) {
            String varName = lexer.yytext();
            TS_entry nodo = ts.pesquisa(varName);
            if (nodo == null) {
                yyerror("(sem) var <" + varName + "> nao declarada");
                return Tp_ERRO;
            } else {
                return nodo.getTipo();
            }
        } else if (token == '(') {
            TS_entry result = parseExp();
            token = lexer.yylex(); // ')'
            return result;
        }
        return Tp_ERRO;
    }

    private boolean isTypeToken(int token) {
        return token == INT || token == DOUBLE || token == FLOAT || 
               token == BOOL || token == STRING || token == IDENT;
    }

    private TS_entry getTypeFromToken(int token) {
        switch (token) {
            case INT: return Tp_INT;
            case DOUBLE: return Tp_DOUBLE;
            case FLOAT: return Tp_FLOAT;
            case BOOL: return Tp_BOOL;
            case STRING: return Tp_STRING;
            case IDENT: 
                String typeName = lexer.yytext();
                TS_entry nodo = ts.pesquisa(typeName);
                if (nodo == null || nodo.getClasse() != ClasseID.NomeStruct) {
                    yyerror("(sem) tipo <" + typeName + "> nao declarado");
                    return Tp_ERRO;
                } else {
                    return nodo;
                }
            default: return Tp_ERRO;
        }
    }

    public static void main(String args[]) throws IOException {
        System.out.println("\n\nVerificador semantico simples\n");

        ParserV2 yyparser;
        if (args.length > 0) {
            yyparser = new ParserV2(new FileReader(args[0]));
        } else {
            System.out.println("[Quit with CTRL-D]");
            System.out.print("Programa de entrada:\n");
            yyparser = new ParserV2(new InputStreamReader(System.in));
        }

        yyparser.yyparse();
        yyparser.listarTS();
        System.out.print("\n\nFeito!\n");
    }
}
