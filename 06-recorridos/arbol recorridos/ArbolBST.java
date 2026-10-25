import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Arbol Binario de Busqueda (BST) con los cuatro recorridos de la Guia 6.
 *
 * Convencion (igual que en las Guias 4 y 5):
 *   - claves MENORES al nodo actual  -> subarbol izquierdo
 *   - claves MAYORES O IGUALES       -> subarbol derecho
 *
 * Recorridos en profundidad (DFS, recursivos):
 *   preorden   : raiz - izquierda - derecha
 *   inorden    : izquierda - raiz - derecha   (en un BST sale ORDENADO)
 *   postorden  : izquierda - derecha - raiz
 * Recorrido en anchura (BFS, con cola):
 *   porNiveles : nivel 0, nivel 1, nivel 2, ...
 *
 * Diferencia con el codigo base de la guia: en lugar de imprimir dentro del
 * recorrido, cada metodo DEVUELVE la secuencia como List<Integer>. Asi se
 * puede imprimir, comparar y probar automaticamente.
 */
public class ArbolBST {

    private NodoArbol<Integer> raiz;

    public NodoArbol<Integer> getRaiz() {
        return raiz;
    }

    // ==================================================================
    // OPERACIONES DEL BST (Guias 4 y 5)
    // ==================================================================

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private NodoArbol<Integer> insertarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return new NodoArbol<>(valor);

        if (valor < nodo.getDato()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), valor));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), valor));
        }
        return nodo;
    }

    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return false;
        if (valor == nodo.getDato()) return true;

        return valor < nodo.getDato()
                ? buscarRec(nodo.getIzquierdo(), valor)
                : buscarRec(nodo.getDerecho(), valor);
    }

    /** Elimina UNA ocurrencia del valor usando el sucesor inorden (Guia 5). */
    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private NodoArbol<Integer> eliminarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return null;

        if (valor < nodo.getDato()) {
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getDato()) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), valor));
        } else {
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();

            int sucesor = minValor(nodo.getDerecho());
            nodo.setDato(sucesor);
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor));
        }
        return nodo;
    }

    public int minValor(NodoArbol<Integer> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("Subarbol vacio: no tiene minimo.");
        }
        NodoArbol<Integer> actual = nodo;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual.getDato();
    }

    // ==================================================================
    // RECORRIDOS EN PROFUNDIDAD (DFS) - RECURSIVOS
    // ==================================================================

    /** Preorden: visita la RAIZ primero, luego izquierda y derecha. */
    public List<Integer> preorden() {
        List<Integer> salida = new ArrayList<>();
        preordenRec(raiz, salida);
        return salida;
    }

    private void preordenRec(NodoArbol<Integer> n, List<Integer> salida) {
        if (n == null) return;
        salida.add(n.getDato());                 // 1) raiz
        preordenRec(n.getIzquierdo(), salida);   // 2) izquierda
        preordenRec(n.getDerecho(), salida);     // 3) derecha
    }

    /** Inorden: izquierda, RAIZ, derecha. En un BST produce claves ordenadas. */
    public List<Integer> inorden() {
        List<Integer> salida = new ArrayList<>();
        inordenRec(raiz, salida);
        return salida;
    }

    private void inordenRec(NodoArbol<Integer> n, List<Integer> salida) {
        if (n == null) return;
        inordenRec(n.getIzquierdo(), salida);    // 1) izquierda
        salida.add(n.getDato());                 // 2) raiz
        inordenRec(n.getDerecho(), salida);      // 3) derecha
    }

    /** Postorden: izquierda, derecha y al FINAL la raiz (hijos antes que el padre). */
    public List<Integer> postorden() {
        List<Integer> salida = new ArrayList<>();
        postordenRec(raiz, salida);
        return salida;
    }

    private void postordenRec(NodoArbol<Integer> n, List<Integer> salida) {
        if (n == null) return;
        postordenRec(n.getIzquierdo(), salida);  // 1) izquierda
        postordenRec(n.getDerecho(), salida);    // 2) derecha
        salida.add(n.getDato());                 // 3) raiz
    }

    // ==================================================================
    // RECORRIDO POR NIVELES (BFS) - CON COLA
    // ==================================================================

    /**
     * Por niveles: visita el arbol por profundidad creciente usando una cola.
     * La cola (FIFO) garantiza que todos los nodos de un nivel salgan antes
     * que los del nivel siguiente.
     */
    public List<Integer> porNiveles() {
        List<Integer> salida = new ArrayList<>();
        if (raiz == null) return salida;

        Queue<NodoArbol<Integer>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            NodoArbol<Integer> actual = cola.poll();      // sale el mas antiguo
            salida.add(actual.getDato());
            if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
            if (actual.getDerecho() != null) cola.add(actual.getDerecho());
        }
        return salida;
    }

    /** Igual que porNiveles(), pero separando cada nivel en su propia lista. */
    public List<List<Integer>> porNivelesAgrupado() {
        List<List<Integer>> niveles = new ArrayList<>();
        if (raiz == null) return niveles;

        Queue<NodoArbol<Integer>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            int cantidadNivel = cola.size();              // nodos de este nivel
            List<Integer> nivel = new ArrayList<>();
            for (int i = 0; i < cantidadNivel; i++) {
                NodoArbol<Integer> actual = cola.poll();
                nivel.add(actual.getDato());
                if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
                if (actual.getDerecho() != null) cola.add(actual.getDerecho());
            }
            niveles.add(nivel);
        }
        return niveles;
    }

    /** Traza paso a paso del recorrido por niveles: que sale y como queda la cola. */
    public List<String> trazarPorNiveles() {
        List<String> pasos = new ArrayList<>();
        if (raiz == null) return pasos;

        Queue<NodoArbol<Integer>> cola = new LinkedList<>();
        cola.add(raiz);
        pasos.add("Inicio                      | cola = [" + raiz.getDato() + "]");

        int paso = 1;
        while (!cola.isEmpty()) {
            NodoArbol<Integer> actual = cola.poll();
            if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
            if (actual.getDerecho() != null) cola.add(actual.getDerecho());

            StringBuilder contenido = new StringBuilder();
            for (NodoArbol<Integer> n : cola) {
                if (contenido.length() > 0) contenido.append(", ");
                contenido.append(n.getDato());
            }
            pasos.add(String.format("Paso %2d: visita %-9d | cola = [%s]",
                    paso++, actual.getDato(), contenido));
        }
        return pasos;
    }

    // ==================================================================
    // EJERCICIO: ELIMINAR UN ARBOL COMPLETO DE FORMA SEGURA
    // ==================================================================

    /**
     * Libera todo el arbol en POSTORDEN: cada nodo se desconecta de sus hijos
     * solo despues de haber liberado a esos hijos. Si primero se soltara el
     * padre, se perderia la referencia a los hijos y quedarian sin liberar
     * (fuga de memoria en lenguajes sin recolector, como C/C++).
     *
     * @return el orden en que se liberaron los nodos (coincide con postorden()).
     */
    public List<Integer> eliminarTodoPostorden() {
        List<Integer> liberados = new ArrayList<>();
        liberarRec(raiz, liberados);
        raiz = null;
        return liberados;
    }

    private void liberarRec(NodoArbol<Integer> n, List<Integer> liberados) {
        if (n == null) return;
        liberarRec(n.getIzquierdo(), liberados);   // primero los hijos...
        liberarRec(n.getDerecho(), liberados);
        liberados.add(n.getDato());                // ...y despues el padre
        n.setIzquierdo(null);
        n.setDerecho(null);
    }

    // ==================================================================
    // UTILIDADES Y VALIDACION
    // ==================================================================

    public boolean esBSTValido() {
        return validarRec(raiz, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validarRec(NodoArbol<Integer> nodo, long minInclusivo, long maxExclusivo) {
        if (nodo == null) return true;
        long v = nodo.getDato();
        if (v < minInclusivo || v >= maxExclusivo) return false;
        return validarRec(nodo.getIzquierdo(), minInclusivo, v)
            && validarRec(nodo.getDerecho(), v, maxExclusivo);
    }

    /** Altura en aristas: vacio = -1, un solo nodo = 0. */
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(alturaRec(nodo.getIzquierdo()), alturaRec(nodo.getDerecho()));
    }

    public int contarNodos() {
        return contarRec(raiz);
    }

    private int contarRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return 0;
        return 1 + contarRec(nodo.getIzquierdo()) + contarRec(nodo.getDerecho());
    }

    /** Dibujo girado 90 grados: raiz a la izquierda, derecha arriba. */
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(arbol vacio)");
            return;
        }
        imprimirRec(raiz, 0);
    }

    private void imprimirRec(NodoArbol<Integer> nodo, int nivel) {
        if (nodo == null) return;
        imprimirRec(nodo.getDerecho(), nivel + 1);
        System.out.println("    ".repeat(nivel) + nodo.getDato());
        imprimirRec(nodo.getIzquierdo(), nivel + 1);
    }

    /** Lineas "dato:izq:der" en preorden (la primera linea es la raiz). */
    public String aEstructura() {
        StringBuilder sb = new StringBuilder();
        estructuraRec(raiz, sb);
        return sb.toString().trim();
    }

    private void estructuraRec(NodoArbol<Integer> n, StringBuilder sb) {
        if (n == null) return;
        sb.append(n.getDato()).append(':')
          .append(n.getIzquierdo() == null ? "-" : n.getIzquierdo().getDato()).append(':')
          .append(n.getDerecho() == null ? "-" : n.getDerecho().getDato()).append('\n');
        estructuraRec(n.getIzquierdo(), sb);
        estructuraRec(n.getDerecho(), sb);
    }
}
