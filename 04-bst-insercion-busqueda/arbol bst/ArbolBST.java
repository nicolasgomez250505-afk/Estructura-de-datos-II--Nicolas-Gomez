/**
 * Arbol Binario de Busqueda (BST) con insercion y busqueda recursivas.
 *
 * Convencion adoptada:
 *   - claves MENORES al nodo actual  -> subarbol izquierdo
 *   - claves MAYORES O IGUALES       -> subarbol derecho
 */
public class ArbolBST {

    private NodoArbol<Integer> raiz;

    // ------------------------------------------------------------------
    // INSERCION (recursiva)
    // ------------------------------------------------------------------

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    /**
     * Caso base: llegamos a una posicion vacia -> se crea el nodo.
     * Caso recursivo: se baja por la izquierda o por la derecha.
     * Se devuelve el nodo para reenlazar el hijo en la llamada anterior.
     */
    private NodoArbol<Integer> insertarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return new NodoArbol<>(valor);

        if (valor < nodo.getDato()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), valor));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), valor));
        }
        return nodo;
    }

    // ------------------------------------------------------------------
    // BUSQUEDA (recursiva)
    // ------------------------------------------------------------------

    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return false;               // no existe
        if (valor == nodo.getDato()) return true;     // encontrado

        return valor < nodo.getDato()
                ? buscarRec(nodo.getIzquierdo(), valor)
                : buscarRec(nodo.getDerecho(), valor);
    }

    /**
     * Misma busqueda, pero registrando el camino recorrido y contando
     * comparaciones (una por cada nodo visitado).
     */
    public ResultadoBusqueda buscarConTraza(int valor) {
        StringBuilder camino = new StringBuilder();
        int[] comparaciones = {0};
        boolean encontrado = buscarTrazaRec(raiz, valor, camino, comparaciones);
        return new ResultadoBusqueda(valor, encontrado, comparaciones[0], camino.toString());
    }

    private boolean buscarTrazaRec(NodoArbol<Integer> nodo, int valor,
                                   StringBuilder camino, int[] comparaciones) {
        if (nodo == null) {
            camino.append(camino.length() == 0 ? "null" : " -> null");
            return false;
        }

        comparaciones[0]++;
        camino.append(camino.length() == 0 ? "" : " -> ").append(nodo.getDato());

        if (valor == nodo.getDato()) return true;

        return valor < nodo.getDato()
                ? buscarTrazaRec(nodo.getIzquierdo(), valor, camino, comparaciones)
                : buscarTrazaRec(nodo.getDerecho(), valor, camino, comparaciones);
    }

    // ------------------------------------------------------------------
    // UTILIDADES PARA EL ANALISIS
    // ------------------------------------------------------------------

    /** Altura en aristas: arbol vacio = -1, un solo nodo = 0. */
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(alturaRec(nodo.getIzquierdo()), alturaRec(nodo.getDerecho()));
    }

    /** Cantidad de nodos del arbol. */
    public int contarNodos() {
        return contarRec(raiz);
    }

    private int contarRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return 0;
        return 1 + contarRec(nodo.getIzquierdo()) + contarRec(nodo.getDerecho());
    }

    /** Inorden: debe imprimir las claves de menor a mayor. */
    public String inorden() {
        StringBuilder sb = new StringBuilder();
        inordenRec(raiz, sb);
        return sb.toString().trim();
    }

    private void inordenRec(NodoArbol<Integer> nodo, StringBuilder sb) {
        if (nodo == null) return;
        inordenRec(nodo.getIzquierdo(), sb);
        sb.append(nodo.getDato()).append(' ');
        inordenRec(nodo.getDerecho(), sb);
    }

    /** Dibujo del arbol girado 90 grados: raiz a la izquierda, derecha arriba. */
    public void imprimirArbol() {
        imprimirRec(raiz, 0);
    }

    private void imprimirRec(NodoArbol<Integer> nodo, int nivel) {
        if (nodo == null) return;
        imprimirRec(nodo.getDerecho(), nivel + 1);
        System.out.println("    ".repeat(nivel) + nodo.getDato());
        imprimirRec(nodo.getIzquierdo(), nivel + 1);
    }

    /** Exporta la estructura como lineas "dato:izq:der" (para graficar). */
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
