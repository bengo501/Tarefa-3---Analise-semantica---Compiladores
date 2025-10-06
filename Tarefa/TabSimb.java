import java.util.ArrayList;
import java.util.Iterator;


public class TabSimb
{
    private ArrayList<TS_entry> lista;
    
    public TabSimb( )
    {
        lista = new ArrayList<TS_entry>();
    }
    
     public void insert( TS_entry nodo ) {
      lista.add(nodo);
    }    
    
    public void listar() {
      System.out.println("\n\nListagem da tabela de simbolos:\n");
      System.out.println("ident      Classe         Escopo      Tipo");
      System.out.println("-----------------------------------------------");
      for (TS_entry nodo : lista) {
          System.out.println(nodo);
      }
    }
      
    public TS_entry pesquisa(String umId) {
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId)) {
	      return nodo;
            }
      }
      return null;
    }

    // Pesquisa campo em um struct específico
    public TS_entry pesquisaCampo(String structId, String campoId) {
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(campoId) && 
              nodo.getClasse() == ClasseID.CampoStruct && 
              nodo.getEscopo().equals(structId)) {
              return nodo;
          }
      }
      return null;
    }

    public  ArrayList<TS_entry> getLista() {return lista;}
}



