import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Pruebas automaticas de los recorridos (sin librerias externas).
 * Cada verificacion imprime [OK] o [FALLO]; si algo falla el programa
 * termina con codigo de salida 1.
 */
public class Pruebas {

    private static int total = 0;
    private static int fallos = 0;

    public static void main(String[] args) {
        System.out.println("=== SECUENCIAS ESPERADAS (derivadas a mano) ===");
        pruebaArbolDe10Productos();
        pruebaArbolDe7Nodos();

        System.out.println("\n=== CASOS DE BORDE ===");
        pruebaArbolVacio();
        pruebaUnSoloNodo();
        pruebaArbolDegenerado();
        pruebaDuplicados();

        System.out.println("\n=== PROPIEDADES ===");
        pruebaInordenOrdenado();
        pruebaPorNivelesAgrupado();
        pruebaEliminarTodoPostorden();

        System.out.println("\n=== PRUEBAS ALEATORIAS (500 arboles) ===");
        pruebaAleatoria();

        System.out.println("\n----------------------------------------");
        System.out.println("Verificaciones: " + total + " | Fallos: " + fallos);
        System.out.println(fallos == 0 ? "RESULTADO: TODAS LAS PRUEBAS PASARON"
                                       : "RESULTADO: HAY FALLOS");
        if (fallos > 0) System.exit(1);
    }

    // ---------------------------------------------------------------
    // Secuencias esperadas
    // ---------------------------------------------------------------

    private static void pruebaArbolDe10Productos() {
        ArbolBST a = construir(4500, 2500, 6500, 1500, 3500, 5500, 8500, 3000, 7500, 9500);
        check("10 productos: preorden",
                a.preorden().equals(lista(4500, 2500, 1500, 3500, 3000, 6500, 5500, 8500, 7500, 9500)));
        check("10 productos: inorden",
                a.inorden().equals(lista(1500, 2500, 3000, 3500, 4500, 5500, 6500, 7500, 8500, 9500)));
        check("10 productos: postorden",
                a.postorden().equals(lista(1500, 3000, 3500, 2500, 5500, 7500, 9500, 8500, 6500, 4500)));
        check("10 productos: por niveles",
                a.porNiveles().equals(lista(4500, 2500, 6500, 1500, 3500, 5500, 8500, 3000, 7500, 9500)));
    }

    private static void pruebaArbolDe7Nodos() {
        ArbolBST a = construir(50, 30, 70, 20, 40, 60, 80);
        check("7 nodos: preorden", a.preorden().equals(lista(50, 30, 20, 40, 70, 60, 80)));
        check("7 nodos: inorden", a.inorden().equals(lista(20, 30, 40, 50, 60, 70, 80)));
        check("7 nodos: postorden", a.postorden().equals(lista(20, 40, 30, 60, 80, 70, 50)));
        check("7 nodos: por niveles", a.porNiveles().equals(lista(50, 30, 70, 20, 40, 60, 80)));
    }

    // ---------------------------------------------------------------
    // Casos de borde
    // ---------------------------------------------------------------

    private static void pruebaArbolVacio() {
        ArbolBST a = new ArbolBST();
        check("Vacio: los cuatro recorridos devuelven lista vacia",
                a.preorden().isEmpty() && a.inorden().isEmpty()
                && a.postorden().isEmpty() && a.porNiveles().isEmpty());
        check("Vacio: porNivelesAgrupado y traza vacios",
                a.porNivelesAgrupado().isEmpty() && a.trazarPorNiveles().isEmpty());
    }

    private static void pruebaUnSoloNodo() {
        ArbolBST a = construir(42);
        List<Integer> esperado = lista(42);
        check("Un nodo: los cuatro recorridos devuelven [42]",
                a.preorden().equals(esperado) && a.inorden().equals(esperado)
                && a.postorden().equals(esperado) && a.porNiveles().equals(esperado));
    }

    private static void pruebaArbolDegenerado() {
        ArbolBST a = construir(1, 2, 3, 4, 5);          // cadena hacia la derecha
        check("Degenerado: preorden = inorden = por niveles",
                a.preorden().equals(lista(1, 2, 3, 4, 5))
                && a.inorden().equals(lista(1, 2, 3, 4, 5))
                && a.porNiveles().equals(lista(1, 2, 3, 4, 5)));
        check("Degenerado: postorden es el reverso", a.postorden().equals(lista(5, 4, 3, 2, 1)));
    }

    private static void pruebaDuplicados() {
        ArbolBST a = construir(5, 5, 5);                // iguales van a la derecha
        check("Duplicados: los cuatro recorridos devuelven 3 elementos",
                a.preorden().size() == 3 && a.inorden().size() == 3
                && a.postorden().size() == 3 && a.porNiveles().size() == 3);
        check("Duplicados: inorden = [5, 5, 5]", a.inorden().equals(lista(5, 5, 5)));
    }

    // ---------------------------------------------------------------
    // Propiedades
    // ---------------------------------------------------------------

    private static void pruebaInordenOrdenado() {
        int[] datos = {4500, 2500, 6500, 1500, 3500, 5500, 8500, 3000, 7500, 9500};
        ArbolBST a = construir(datos);
        List<Integer> ordenada = new ArrayList<>();
        for (int v : datos) ordenada.add(v);
        Collections.sort(ordenada);
        check("Ejercicio 2: inorden == lista ordenada", a.inorden().equals(ordenada));
    }

    private static void pruebaPorNivelesAgrupado() {
        ArbolBST a = construir(4500, 2500, 6500, 1500, 3500, 5500, 8500, 3000, 7500, 9500);
        List<List<Integer>> niveles = a.porNivelesAgrupado();
        check("Agrupado: 4 niveles con 1, 2, 4 y 3 nodos",
                niveles.size() == 4 && niveles.get(0).size() == 1 && niveles.get(1).size() == 2
                && niveles.get(2).size() == 4 && niveles.get(3).size() == 3);
        check("Agrupado: nivel 1 = [2500, 6500]", niveles.get(1).equals(lista(2500, 6500)));
        check("Agrupado: niveles == altura + 1", niveles.size() == a.altura() + 1);
        List<Integer> aplanado = new ArrayList<>();
        niveles.forEach(aplanado::addAll);
        check("Agrupado: aplanado == porNiveles()", aplanado.equals(a.porNiveles()));
        check("Traza: un paso por nodo mas el inicio",
                a.trazarPorNiveles().size() == a.contarNodos() + 1);
    }

    private static void pruebaEliminarTodoPostorden() {
        ArbolBST a = construir(4500, 2500, 6500, 1500, 3500, 5500, 8500, 3000, 7500, 9500);
        List<Integer> esperado = a.postorden();
        List<Integer> liberados = a.eliminarTodoPostorden();
        check("Ejercicio 3: orden de liberacion == postorden", liberados.equals(esperado));
        check("Ejercicio 3: el arbol queda vacio", a.getRaiz() == null && a.contarNodos() == 0);
        check("Ejercicio 3: eliminar un arbol vacio no falla", a.eliminarTodoPostorden().isEmpty());
    }

    // ---------------------------------------------------------------
    // Aleatorias
    // ---------------------------------------------------------------

    /** Compara los recorridos con propiedades que deben cumplirse en CUALQUIER BST. */
    private static void pruebaAleatoria() {
        Random rnd = new Random(2026);
        boolean ordenado = true, mismosElementos = true, raices = true;
        boolean reconstruccion = true, niveles = true, reflejo = true;

        for (int t = 0; t < 500; t++) {
            int n = 1 + rnd.nextInt(40);
            int[] datos = new int[n];
            for (int i = 0; i < n; i++) datos[i] = rnd.nextInt(100);
            ArbolBST a = construir(datos);

            List<Integer> pre = a.preorden(), in = a.inorden();
            List<Integer> post = a.postorden(), niv = a.porNiveles();

            // inorden ordenado y con los mismos elementos que se insertaron
            ordenado &= esOrdenada(in);
            // los cuatro recorridos contienen exactamente los mismos elementos
            mismosElementos &= pre.size() == n && post.size() == n && niv.size() == n
                    && ordenadaDe(pre).equals(in) && ordenadaDe(post).equals(in)
                    && ordenadaDe(niv).equals(in);
            // la raiz aparece primero en preorden y niveles, y al final en postorden
            int raiz = a.getRaiz().getDato();
            raices &= pre.get(0) == raiz && niv.get(0) == raiz && post.get(n - 1) == raiz;
            // insertar en preorden o por niveles reconstruye el mismo arbol
            reconstruccion &= construir(aArray(pre)).aEstructura().equals(a.aEstructura())
                    && construir(aArray(niv)).aEstructura().equals(a.aEstructura());
            // por niveles agrupado: cantidad de niveles = altura + 1
            niveles &= a.porNivelesAgrupado().size() == a.altura() + 1;
            // el arbol sigue siendo un BST valido
            reflejo &= a.esBSTValido();
        }
        check("Aleatorio: inorden siempre ordenado", ordenado);
        check("Aleatorio: los cuatro recorridos tienen los mismos elementos", mismosElementos);
        check("Aleatorio: raiz primera (preorden, niveles) y ultima (postorden)", raices);
        check("Aleatorio: insertar en preorden / por niveles reconstruye el arbol", reconstruccion);
        check("Aleatorio: cantidad de niveles == altura + 1", niveles);
        check("Aleatorio: el arbol es un BST valido", reflejo);
    }

    // ---------------------------------------------------------------

    private static ArbolBST construir(int... valores) {
        ArbolBST a = new ArbolBST();
        for (int v : valores) a.insertar(v);
        return a;
    }

    private static List<Integer> lista(Integer... valores) {
        return new ArrayList<>(Arrays.asList(valores));
    }

    private static int[] aArray(List<Integer> lista) {
        return lista.stream().mapToInt(Integer::intValue).toArray();
    }

    private static List<Integer> ordenadaDe(List<Integer> lista) {
        List<Integer> copia = new ArrayList<>(lista);
        Collections.sort(copia);
        return copia;
    }

    private static boolean esOrdenada(List<Integer> lista) {
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i - 1) > lista.get(i)) return false;
        }
        return true;
    }

    private static void check(String nombre, boolean condicion) {
        total++;
        if (!condicion) fallos++;
        System.out.println((condicion ? "[OK]    " : "[FALLO] ") + nombre);
    }
}
