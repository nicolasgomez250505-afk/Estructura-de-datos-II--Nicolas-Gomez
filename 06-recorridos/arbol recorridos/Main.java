import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Guia 6. Recorridos en arboles binarios.
 *
 * Construye un BST de 10 productos (por codigo), ejecuta los cuatro recorridos
 * sobre el MISMO arbol, registra las secuencias y resuelve los ejercicios.
 */
public class Main {

    /** Catalogo de productos: codigo -> nombre (el BST se ordena por codigo). */
    private static final Map<Integer, String> CATALOGO = new LinkedHashMap<>();

    static {
        CATALOGO.put(4500, "Mochila");
        CATALOGO.put(2500, "Lapiz");
        CATALOGO.put(6500, "Marcadores");
        CATALOGO.put(1500, "Cuaderno");
        CATALOGO.put(3500, "Regla");
        CATALOGO.put(5500, "Calculadora");
        CATALOGO.put(8500, "Pegante");
        CATALOGO.put(3000, "Borrador");
        CATALOGO.put(7500, "Tijeras");
        CATALOGO.put(9500, "Carpeta");
    }

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("docs"));

        // ------------------------------------------------------------
        // EJERCICIO 1: arbol de 10 productos por codigo
        // ------------------------------------------------------------
        int[] ordenInsercion = CATALOGO.keySet().stream().mapToInt(Integer::intValue).toArray();
        ArbolBST arbol = construir(ordenInsercion);

        titulo("EJERCICIO 1: BST DE 10 PRODUCTOS POR CODIGO");
        System.out.println("Orden de insercion: " + Arrays.toString(ordenInsercion));
        System.out.println("Nodos: " + arbol.contarNodos() + " | Altura: " + arbol.altura()
                + " | BST valido: " + arbol.esBSTValido());
        System.out.println();
        arbol.imprimirArbol();

        // ------------------------------------------------------------
        // PASOS 2 a 5: los cuatro recorridos sobre el mismo arbol
        // ------------------------------------------------------------
        List<Integer> pre = arbol.preorden();
        List<Integer> in = arbol.inorden();
        List<Integer> post = arbol.postorden();
        List<Integer> niv = arbol.porNiveles();

        titulo("SECUENCIAS DE LOS CUATRO RECORRIDOS");
        System.out.println("Preorden   (raiz-izq-der): " + formato(pre));
        System.out.println("Inorden    (izq-raiz-der): " + formato(in));
        System.out.println("Postorden  (izq-der-raiz): " + formato(post));
        System.out.println("Por niveles (BFS, cola)  : " + formato(niv));

        titulo("RECORRIDO POR NIVELES, NIVEL A NIVEL");
        List<List<Integer>> niveles = arbol.porNivelesAgrupado();
        for (int i = 0; i < niveles.size(); i++) {
            System.out.println("Nivel " + i + ": " + formato(niveles.get(i)));
        }

        titulo("TRAZA DE LA COLA EN EL RECORRIDO POR NIVELES");
        arbol.trazarPorNiveles().forEach(System.out::println);

        titulo("CATALOGO EN ORDEN DE CODIGO (INORDEN)");
        for (int codigo : in) {
            System.out.printf("%5d  %s%n", codigo, CATALOGO.get(codigo));
        }

        // ------------------------------------------------------------
        // EJERCICIO 2: comparar inorden con una lista ordenada
        // ------------------------------------------------------------
        titulo("EJERCICIO 2: INORDEN FRENTE A UNA LISTA ORDENADA");
        List<Integer> listaOrdenada = new ArrayList<>();
        for (int v : ordenInsercion) listaOrdenada.add(v);
        Collections.sort(listaOrdenada);
        System.out.println("Lista ordenada: " + formato(listaOrdenada));
        System.out.println("Inorden       : " + formato(in));
        System.out.println("Son iguales?  : " + in.equals(listaOrdenada));

        // ------------------------------------------------------------
        // RELACIONES ENTRE LAS SALIDAS
        // ------------------------------------------------------------
        titulo("RELACIONES ENTRE LAS SALIDAS");
        int raiz = arbol.getRaiz().getDato();
        System.out.println("Raiz del arbol                    : " + raiz);
        System.out.println("Primer elemento de preorden       : " + pre.get(0));
        System.out.println("Ultimo elemento de postorden      : " + post.get(post.size() - 1));
        System.out.println("Primer elemento por niveles       : " + niv.get(0));
        System.out.println("Postorden es el reflejo de preorden con hijos intercambiados: "
                + postordenEsReflejo(arbol));
        ArbolBST desdePreorden = construir(pre.stream().mapToInt(Integer::intValue).toArray());
        ArbolBST desdeNiveles = construir(niv.stream().mapToInt(Integer::intValue).toArray());
        ArbolBST desdePostorden = construir(post.stream().mapToInt(Integer::intValue).toArray());
        System.out.println("Insertar en PREORDEN reconstruye el mismo arbol  : "
                + desdePreorden.aEstructura().equals(arbol.aEstructura()));
        System.out.println("Insertar en NIVELES reconstruye el mismo arbol   : "
                + desdeNiveles.aEstructura().equals(arbol.aEstructura()));
        System.out.println("Insertar en POSTORDEN reconstruye el mismo arbol : "
                + desdePostorden.aEstructura().equals(arbol.aEstructura()));

        // ------------------------------------------------------------
        // EJERCICIO 3: eliminar un arbol completo de forma segura
        // ------------------------------------------------------------
        titulo("EJERCICIO 3: ELIMINAR EL ARBOL COMPLETO (POSTORDEN)");
        ArbolBST aEliminar = construir(ordenInsercion);
        System.out.println("Nodos antes     : " + aEliminar.contarNodos());
        List<Integer> liberados = aEliminar.eliminarTodoPostorden();
        System.out.println("Orden de liberacion: " + formato(liberados));
        System.out.println("Coincide con postorden: " + liberados.equals(post));
        System.out.println("Nodos despues   : " + aEliminar.contarNodos()
                + " | raiz == null: " + (aEliminar.getRaiz() == null));

        // ------------------------------------------------------------
        // Archivos de apoyo (diagramas y tabla)
        // ------------------------------------------------------------
        Files.writeString(Path.of("docs/estructura_10productos.txt"),
                arbol.aEstructura(), StandardCharsets.UTF_8);
        Files.writeString(Path.of("docs/secuencias.csv"),
                "Recorrido,Secuencia\n"
                + "Preorden," + formato(pre) + "\n"
                + "Inorden," + formato(in) + "\n"
                + "Postorden," + formato(post) + "\n"
                + "Por niveles," + formato(niv) + "\n",
                StandardCharsets.UTF_8);
    }

    // ---------------------------------------------------------------
    // Utilidades
    // ---------------------------------------------------------------

    private static ArbolBST construir(int[] valores) {
        ArbolBST arbol = new ArbolBST();
        for (int v : valores) arbol.insertar(v);
        return arbol;
    }

    private static String formato(List<Integer> lista) {
        StringBuilder sb = new StringBuilder();
        for (int v : lista) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(v);
        }
        return sb.toString();
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("================================================");
        System.out.println(texto);
        System.out.println("================================================");
    }

    /** Postorden == reverso del recorrido "raiz-derecha-izquierda". */
    private static boolean postordenEsReflejo(ArbolBST arbol) {
        List<Integer> reflejo = new ArrayList<>();
        reflejoRec(arbol.getRaiz(), reflejo);
        Collections.reverse(reflejo);
        return reflejo.equals(arbol.postorden());
    }

    private static void reflejoRec(NodoArbol<Integer> n, List<Integer> salida) {
        if (n == null) return;
        salida.add(n.getDato());
        reflejoRec(n.getDerecho(), salida);
        reflejoRec(n.getIzquierdo(), salida);
    }
}
