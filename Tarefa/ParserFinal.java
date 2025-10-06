import java.io.*;

public class ParserFinal {
    private TabSimb ts;

    public static TS_entry Tp_INT = new TS_entry("int", null, ClasseID.TipoBase);
    public static TS_entry Tp_DOUBLE = new TS_entry("double", null, ClasseID.TipoBase);
    public static TS_entry Tp_FLOAT = new TS_entry("float", null, ClasseID.TipoBase);
    public static TS_entry Tp_BOOL = new TS_entry("bool", null, ClasseID.TipoBase);
    public static TS_entry Tp_STRING = new TS_entry("string", null, ClasseID.TipoBase);
    public static TS_entry Tp_ARRAY = new TS_entry("array", null, ClasseID.TipoBase);
    public static TS_entry Tp_STRUCT = new TS_entry("struct", null, ClasseID.TipoBase);
    public static TS_entry Tp_ERRO = new TS_entry("_erro_", null, ClasseID.TipoBase);

    private String currStructName;
    private int lineNumber = 1;

    public ParserFinal() {
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
        System.err.printf("Erro (linha: %2d) \tMensagem: %s\n", lineNumber, error);
    }

    public void listarTS() { 
        ts.listar();
    }

    public void parseFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            line = line.trim();
            
            if (line.isEmpty() || line.startsWith("//")) {
                continue;
            }
            
            if (line.startsWith("struct ")) {
                parseStructDeclaration(line);
                parseStructFields(reader);
            } else if (line.startsWith("void main()")) {
                parseMainFunction(reader);
                break;
            } else if (isTypeDeclaration(line)) {
                parseVariableDeclaration(line);
            }
        }
        
        reader.close();
    }
    

    private void parseStructDeclaration(String line) {
        // struct NOME {
        String[] parts = line.split("\\s+");
        if (parts.length >= 2) {
            String structName = parts[1];
            // Remove { se presente
            if (structName.endsWith("{")) {
                structName = structName.substring(0, structName.length() - 1);
            }
            TS_entry structNodo = ts.pesquisa(structName);
            if (structNodo != null) {
                yyerror("(sem) struct <" + structName + "> já declarado");
            } else {
                currStructName = structName;
                TS_entry novoStruct = new TS_entry(structName, Tp_STRUCT, ClasseID.NomeStruct, 0, null, "-");
                ts.insert(novoStruct);
            }
        }
    }

    private void parseStructFields(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            line = line.trim();
            
            if (line.equals("};")) {
                break;
            }
            
            if (line.endsWith(";")) {
                parseFieldDeclaration(line);
            }
        }
    }

    private void parseFieldDeclaration(String line) {
        // tipo campo;
        line = line.substring(0, line.length() - 1); // remove ';'
        String[] parts = line.trim().split("\\s+");
        
        if (parts.length >= 2) {
            String tipoStr = parts[0];
            String campoName = parts[1];
            
            TS_entry tipo = getTypeFromString(tipoStr);
            TS_entry campo = new TS_entry(campoName, tipo, ClasseID.CampoStruct, 0, null, currStructName);
            ts.insert(campo);
        }
    }

    private void parseVariableDeclaration(String line) {
        // tipo variavel;
        if (line.endsWith(";")) {
            line = line.substring(0, line.length() - 1); // remove ';'
            String[] parts = line.trim().split("\\s+");
            
            if (parts.length >= 2) {
                String tipoStr = parts[0];
                String varName = parts[1];
                
                TS_entry tipo = getTypeFromString(tipoStr);
                TS_entry nodo = ts.pesquisa(varName);
                if (nodo != null) {
                    yyerror("(sem) variavel >" + varName + "< jah declarada");
                } else {
                    ts.insert(new TS_entry(varName, tipo, ClasseID.VarGlobal));
                }
            }
        }
    }

    private void parseMainFunction(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            line = line.trim();
            
            if (line.equals("}")) {
                break;
            }
            
            if (line.endsWith(";") && !line.isEmpty()) {
                parseStatement(line);
            }
        }
    }

    private boolean isTypeDeclaration(String line) {
        return line.startsWith("int ") || line.startsWith("float ") || 
               line.startsWith("bool ") || line.startsWith("string ") ||
               line.startsWith("double ") || isStructTypeDeclaration(line);
    }

    private boolean isStructTypeDeclaration(String line) {
        // Verifica se é uma declaração de variável de tipo struct
        for (TS_entry entry : ts.getLista()) {
            if (entry.getClasse() == ClasseID.NomeStruct) {
                if (line.startsWith(entry.getId() + " ")) {
                    return true;
                }
            }
        }
        return false;
    }

    private void parseStatement(String line) {
        // Remove ';' do final
        line = line.substring(0, line.length() - 1).trim();
        
        if (line.contains("=")) {
            parseAssignment(line);
        }
    }
    
    private void parseAssignment(String line) {
        String[] parts = line.split("=", 2);
        if (parts.length == 2) {
            String leftSide = parts[0].trim();
            String rightSide = parts[1].trim();
            
            TS_entry leftType = getExpressionType(leftSide);
            TS_entry rightType = getExpressionType(rightSide);
            
            if (leftType != null && rightType != null) {
                validateAssignment(leftType, rightType);
            }
        }
    }
    
    private TS_entry getExpressionType(String expression) {
        expression = expression.trim();
        
        // Se é um número
        if (expression.matches("\\d+")) {
            return Tp_INT;
        }
        
        // Se contém operador de soma
        if (expression.contains("+")) {
            return parseBinaryExpression(expression, "+");
        }
        
        // Se é acesso a campo (var.campo)
        if (expression.contains(".")) {
            return parseFieldAccess(expression);
        }
        
        // Se é apenas uma variável
        TS_entry var = ts.pesquisa(expression);
        if (var != null) {
            return var.getTipo();
        }
        
        yyerror("(sem) variavel <" + expression + "> nao declarada");
        return Tp_ERRO;
    }
    
    private TS_entry parseBinaryExpression(String expression, String operator) {
        String[] parts = expression.split("\\" + operator);
        if (parts.length == 2) {
            TS_entry leftType = getExpressionType(parts[0].trim());
            TS_entry rightType = getExpressionType(parts[1].trim());
            
            if (leftType == Tp_INT && rightType == Tp_INT) {
                return Tp_INT;
            } else if ((leftType == Tp_FLOAT && (rightType == Tp_INT || rightType == Tp_FLOAT)) ||
                      (rightType == Tp_FLOAT && (leftType == Tp_INT || leftType == Tp_FLOAT))) {
                return Tp_FLOAT;
            } else if ((leftType == Tp_DOUBLE && (rightType == Tp_INT || rightType == Tp_DOUBLE)) ||
                      (rightType == Tp_DOUBLE && (leftType == Tp_INT || leftType == Tp_DOUBLE))) {
                return Tp_DOUBLE;
            } else {
                yyerror("(sem) tipos incomp. para soma: " + leftType.getTipoStr() + " + " + rightType.getTipoStr());
                return Tp_ERRO;
            }
        }
        return Tp_ERRO;
    }
    
    private TS_entry parseFieldAccess(String expression) {
        String[] parts = expression.split("\\.");
        if (parts.length >= 2) {
            String varName = parts[0].trim();
            
            TS_entry var = ts.pesquisa(varName);
            if (var == null) {
                yyerror("(sem) variavel <" + varName + "> nao declarada");
                return Tp_ERRO;
            }
            
            if (var.getTipo() == null || var.getTipo().getClasse() != ClasseID.NomeStruct) {
                yyerror("(sem) variavel <" + varName + "> nao e struct");
                return Tp_ERRO;
            }
            
            // Processa acesso aninhado (ex: alu1.dnasc.ano)
            TS_entry currentType = var.getTipo();
            for (int i = 1; i < parts.length; i++) {
                String fieldName = parts[i].trim();
                
                if (currentType == null || currentType.getClasse() != ClasseID.NomeStruct) {
                    yyerror("(sem) campo <" + parts[i-1] + "> nao e struct");
                    return Tp_ERRO;
                }
                
                TS_entry field = ts.pesquisaCampo(currentType.getId(), fieldName);
                if (field == null) {
                    yyerror("(sem) campo <" + fieldName + "> nao existe no struct <" + currentType.getId() + ">");
                    return Tp_ERRO;
                }
                
                currentType = field.getTipo();
            }
            
            return currentType;
        }
        return Tp_ERRO;
    }
    
    private void validateAssignment(TS_entry leftType, TS_entry rightType) {
        if (leftType == Tp_ERRO || rightType == Tp_ERRO) {
            return; // Já foi reportado erro anteriormente
        }
        
        // Verifica compatibilidade de tipos
        if (leftType == rightType) {
            return; // Tipos iguais, OK
        }
        
        // Verifica conversões implícitas permitidas
        if ((leftType == Tp_FLOAT && (rightType == Tp_INT || rightType == Tp_FLOAT)) ||
            (leftType == Tp_DOUBLE && (rightType == Tp_INT || rightType == Tp_DOUBLE || rightType == Tp_FLOAT))) {
            return; // Conversões numéricas permitidas
        }
        
        yyerror("(sem) tipos incomp. para atribuicao: " + leftType.getTipoStr() + " = " + rightType.getTipoStr());
    }

    private TS_entry getTypeFromString(String tipoStr) {
        switch (tipoStr) {
            case "int": return Tp_INT;
            case "double": return Tp_DOUBLE;
            case "float": return Tp_FLOAT;
            case "bool": return Tp_BOOL;
            case "string": return Tp_STRING;
            default: 
                // Verifica se é um tipo struct
                TS_entry nodo = ts.pesquisa(tipoStr);
                if (nodo != null && nodo.getClasse() == ClasseID.NomeStruct) {
                    return nodo;
                } else {
                    // Se não encontrou, retorna erro
                    return Tp_ERRO;
                }
        }
    }

    public static void main(String args[]) throws IOException {
        System.out.println("\n\nVerificador semantico simples\n");

        ParserFinal parser;
        if (args.length > 0) {
            parser = new ParserFinal();
            parser.parseFile(args[0]);
        } else {
            System.out.println("Uso: java ParserFinal <arquivo>");
            return;
        }

        parser.listarTS();
        System.out.print("\n\nFeito!\n");
    }
}
