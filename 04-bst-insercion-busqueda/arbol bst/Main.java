import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Guia 4. BST I: insercion y busqueda.
 * Compara un BST construido con codigos en orden aleatorio
 * frente a otro construido con los MISMOS codigos ya ordenados.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // ------------------------------------------------------------
        // EJERCICIO 1: 50,30,70,20,40,60,80
        // ------------------------------------------------------------
        System.out.println("=== EJERCICIO 1: BST DE 7 NODOS ===");
        ArbolBST bst7 = new ArbolBST();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            bst7.insertar(v);
        }
        bst7.imprimirArbol();
        System.out.println("Inorden: " + bst7.inorden());
        System.out.println("Altura : " + bst7.altura());

        System.out.println("\n=== EJERCICIO 2: CAMINO DE BUSQUEDA ===");
        System.out.println(bst7.buscarConTraza(60));   // existente
        System.out.println(bst7.buscarConTraza(65));   // inexistente

        // ------------------------------------------------------------
        // PASOS 5 y 6: 15 codigos de productos
        // ------------------------------------------------------------
        int[] aleatorio = {
            3900, 1200, 7200, 5000, 900,
            8500, 2300, 6100, 4500, 1800,
            7800, 3100, 5600, 2700, 6700
        };
        int[] ordenado = aleatorio.clone();
        Arrays.sort(ordenado);

        // Cinco busquedas: 4 codigos existentes y 1 inexistente
        int[] consultas = {900, 3100, 6100, 8500, 9999};

        ArbolBST bstAleatorio = construir(aleatorio);
        ArbolBST bstOrdenado = construir(ordenado);

        System.out.println("\n=== 15 CODIGOS EN ORDEN ALEATORIO ===");
        System.out.println("Orden de insercion: " + Arrays.toString(aleatorio));
        bstAleatorio.imprimirArbol();
        int compA = probar(bstAleatorio, consultas);

        System.out.println("\n=== 15 CODIGOS YA ORDENADOS ===");
        System.out.println("Orden de insercion: " + Arrays.toString(ordenado));
        bstOrdenado.imprimirArbol();
        int compO = probar(bstOrdenado, consultas);

        // ------------------------------------------------------------
        // TABLA COMPARATIVA
        // ------------------------------------------------------------
        System.out.println("\n=== TABLA COMPARATIVA ===");
        System.out.printf("%-18s %6s %8s %14s %10s%n",
                "Escenario", "Nodos", "Altura", "Comparaciones", "Promedio");
        fila("Orden aleatorio", bstAleatorio, compA, consultas.length);
        fila("Orden ordenado", bstOrdenado, compO, consultas.length);

        System.out.println("\nConclusion: insertar datos ya ordenados degenera el BST "
                + "en una lista enlazada y acerca las operaciones a O(n).");

        // Archivos de apoyo para generar diagramas y la tabla CSV
        Files.writeString(Path.of("docs/estructura_aleatorio.txt"),
                bstAleatorio.aEstructura(), StandardCharsets.UTF_8);
        Files.writeString(Path.of("docs/estructura_ordenado.txt"),
                bstOrdenado.aEstructura(), StandardCharsets.UTF_8);
        Files.writeString(Path.of("docs/estructura_7nodos.txt"),
                bst7.aEstructura(), StandardCharsets.UTF_8);
        Files.writeString(Path.of("docs/comparacion_bst.csv"),
                "Escenario,Nodos,Altura (aristas),Comparaciones totales,Promedio en 5 busquedas\n"
                + csv("Orden aleatorio", bstAleatorio, compA, consultas.length)
                + csv("Orden ordenado", bstOrdenado, compO, consultas.length),
                StandardCharsets.UTF_8);
    }

    private static ArbolBST construir(int[] datos) {
        ArbolBST arbol = new ArbolBST();
        for (int v : datos) arbol.insertar(v);
        return arbol;
    }

    private static int probar(ArbolBST arbol, int[] consultas) {
        int total = 0;
        for (int c : consultas) {
            ResultadoBusqueda r = arbol.buscarConTraza(c);
            System.out.println(r);
            total += r.getComparaciones();
        }
        return total;
    }

    private static void fila(String nombre, ArbolBST a, int comp, int n) {
        System.out.printf("%-18s %6d %8d %14d %10.1f%n",
                nombre, a.contarNodos(), a.altura(), comp, comp / (double) n);
    }

    private static String csv(String nombre, ArbolBST a, int comp, int n) {
        return nombre + "," + a.contarNodos() + "," + a.altura() + ","
                + comp + "," + String.format(java.util.Locale.US, "%.1f", comp / (double) n) + "\n";
    }
}
