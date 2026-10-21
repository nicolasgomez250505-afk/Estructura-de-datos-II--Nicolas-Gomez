import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Guia 5. BST II: eliminacion de nodos.
 *
 * Se prueba cada caso por separado, mostrando el arbol ANTES y DESPUES:
 *   Caso 1: nodo hoja
 *   Caso 2: nodo con un hijo
 *   Caso 3: nodo con dos hijos
 *   Ejercicio: eliminar la raiz y validar que siga ordenado
 *
 * Ademas guarda la estructura de cada arbol en docs/ para generar los diagramas.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("docs"));

        // CASO 1: hoja ------------------------------------------------
        probar("CASO 1: ELIMINAR UN NODO HOJA", "caso1_hoja",
                crearBase(), 20, "Hoja: no tiene hijos.");

        // CASO 2: un hijo ---------------------------------------------
        // Se agrega 65 para que 60 tenga un unico hijo (el derecho).
        ArbolBST conUnHijo = crearBase();
        conUnHijo.insertar(65);
        probar("CASO 2: ELIMINAR UN NODO CON UN HIJO", "caso2_un_hijo",
                conUnHijo, 60, "60 tiene un solo hijo: 65.");

        // CASO 3: dos hijos (no raiz) ---------------------------------
        probar("CASO 3: ELIMINAR UN NODO CON DOS HIJOS", "caso3_dos_hijos",
                crearBase(), 30, "30 tiene dos hijos: 20 y 40. Sucesor inorden = 40.");

        // EJERCICIO: eliminar la raiz ---------------------------------
        // Con 65 presente, el sucesor (60) tiene un hijo: el caso 3
        // termina usando internamente el caso 2.
        ArbolBST conRaiz = crearBase();
        conRaiz.insertar(65);
        probar("EJERCICIO: ELIMINAR LA RAIZ", "raiz",
                conRaiz, 50, "La raiz 50 tiene dos hijos. Sucesor inorden = 60 (con hijo 65).");

        // EXTRA: valor inexistente ------------------------------------
        System.out.println("================================================");
        System.out.println("EXTRA: ELIMINAR UN VALOR QUE NO EXISTE");
        System.out.println("================================================");
        ArbolBST a = crearBase();
        int antes = a.contarNodos();
        a.eliminar(999);
        System.out.println("Nodos antes: " + antes + " | despues: " + a.contarNodos()
                + " | BST valido: " + a.esBSTValido());
    }

    /** Ejecuta una eliminacion mostrando ANTES / DESPUES y las validaciones. */
    private static void probar(String titulo, String archivo, ArbolBST arbol,
                               int valor, String nota) throws IOException {

        System.out.println("================================================");
        System.out.println(titulo);
        System.out.println("================================================");
        System.out.println(nota);

        System.out.println("\nANTES de eliminar " + valor + ":");
        arbol.imprimirArbol();
        System.out.println("Inorden: " + arbol.inorden());
        Files.writeString(Path.of("docs/estructura_" + archivo + "_antes.txt"),
                arbol.aEstructura(), StandardCharsets.UTF_8);

        int nodosAntes = arbol.contarNodos();
        arbol.eliminar(valor);

        System.out.println("\nDESPUES de eliminar " + valor + ":");
        arbol.imprimirArbol();
        System.out.println("Inorden: " + arbol.inorden());
        Files.writeString(Path.of("docs/estructura_" + archivo + "_despues.txt"),
                arbol.aEstructura(), StandardCharsets.UTF_8);

        System.out.println("Raiz actual        : " + arbol.getRaiz().getDato());
        System.out.println("Nodos antes/despues: " + nodosAntes + " / " + arbol.contarNodos());
        System.out.println("Existe " + valor + "?        : " + arbol.buscar(valor));
        System.out.println("BST valido?        : " + arbol.esBSTValido());
        System.out.println();
    }

    /**
     * BST base (el mismo de la Guia 4):
     *
     *           50
     *         /    \
     *       30      70
     *      /  \    /  \
     *    20   40  60   80
     */
    private static ArbolBST crearBase() {
        ArbolBST arbol = new ArbolBST();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            arbol.insertar(v);
        }
        return arbol;
    }
}
