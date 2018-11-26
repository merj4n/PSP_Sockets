import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListarArchivos {


    public ListarArchivos() { }

    public static List<String> buscador(String nombre, File raiz){
        List<String> listaArchivos = new ArrayList<>();
        File[] lista = raiz.listFiles();

        if(lista != null) {
            for(File elemento : lista) {
                listaArchivos.add(elemento.getName());
            }
        }
        return listaArchivos;
    }
}
