import java.io.*;
import java.util.*;

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
        
        // Primeira passada: processar todos os structs
        parseAllStructs(reader);
        
        // Segunda passada: processar declarações de variáveis
        reader = new BufferedReader(new FileReader(filename));
        parseVariablesAndMain(reader);
        
        reader.close();
    }
    
    private void parseAllStructs(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            line = line.trim();
            
            if (line.isEmpty() || line.startsWith("//")) {
                continue;
            }
            
            if (line.startsWith("struct ")) {
                parseStructDeclaration(line);
                // Não processar campos aqui, eles serão processados na segunda passada
            } else if (line.startsWith("void main()")) {
                break;
            }
        }
    }
    
    private void parseVariablesAndMain(BufferedReader reader) throws IOException {
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
            // Por simplicidade, não processamos as instruções dentro do main
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
