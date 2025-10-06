import java.util.ArrayList;
/**
 * Write a description of class Paciente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TS_entry
{
   private String id;
   private ClasseID classe;  
   private TS_entry tipo;
   private String escopo;  // Para campos de struct

   private int nroElementos;
   private TS_entry tipoBase;

   private TabSimb locals;
   


   // construtor básico
   public TS_entry(String umId, TS_entry umTipo, ClasseID umaClasse) {
      this(umId, umTipo, umaClasse, 0, null, "-");
   }

   // construtor para arrays
   public TS_entry(String umId, TS_entry umTipo, ClasseID umaClasse, int elems, TS_entry tp) {
      this(umId, umTipo, umaClasse, elems, tp, "-");
   }

   // construtor completo
   public TS_entry(String umId, TS_entry umTipo, ClasseID umaClasse, int elems, TS_entry tp, String esc) {
      id = umId;
      tipo = umTipo;
      classe = umaClasse;
      nroElementos = elems;
      tipoBase = tp;
      escopo = esc;
   }


   public String getId() {
       return id; 
   }

   public TS_entry getTipo() {
       return tipo; 
   }
   
   public TS_entry getTipoBase() {
       return tipoBase; 
   }

   public String getEscopo() {
       return escopo;
   }

   public ClasseID getClasse() {
       return classe;
   }
   
    
   public String toString() {
       StringBuilder aux = new StringBuilder("");
        
       aux.append(String.format("%-10s", id));
       aux.append(String.format("%-12s", classe));
       aux.append(String.format("%-10s", escopo));
       aux.append(tipo2str(this.tipo)); 
       
      return aux.toString();

   }

  public String getTipoStr() {
       return tipo2str(this); 
   }

    public String tipo2str(TS_entry tipo) {
      TS_entry tmp;
      if (tipo == null)  return "null"; 
      else if (tipo.getId().equals("int"))    return "int"; 
      else if (tipo.getId().equals("bool"))   return "boolean"; 
      else if (tipo.getId().equals("double"))  return "double";
      else if (tipo.getId().equals("float"))  return "float";
      else if (tipo.getId().equals("string"))  return "string";
      else if (tipo.getId().equals("struct"))  return "struct";
      else if (tipo.getTipo() != null) return  String.format("array(%d,%s)",
                                                   tipo.nroElementos, 
                                                    tipo2str(tipo.tipoBase));
                 
      else if (tipo.getId().equals("_erro_"))  return  "_erro_";
      else if (tipo.getClasse() == ClasseID.NomeStruct) return tipo.getId();
      else                             return "erro/tp";
   }

}






