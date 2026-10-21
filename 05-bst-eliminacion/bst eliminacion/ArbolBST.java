import java.util.ArrayList;
import java.util.List;

/**
 * Arbol Binario de Busqueda (BST): insercion, busqueda y ELIMINACION.
 *
 * Convencion adoptada (la misma de la Guia 4):
 *   - claves MENORES al nodo actual  -> subarbol izquierdo
 *   - claves MAYORES O IGUALES       -> subarbol derecho
 */
public class ArbolBST {

    private NodoArbol<Integer> raiz;

    public NodoArbol<Integer> getRaiz() {
        return raiz;
    }

    // ==================================================================
    // INSERCION (Guia 4)
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

    // ==================================================================
    // BUSQUEDA (Guia 4)
    // ==================================================================

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

    /** Busqueda que registra camino y comparaciones (Guia 4). */
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

    // ==================================================================
    // ELIMINACION (Guia 5)
    // ==================================================================

    /**
     * Elimina UNA ocurrencia de 'valor' usando el sucesor inorden.
     * Si el valor no existe, el arbol no cambia.
     * Se reasigna la raiz porque el nodo eliminado puede ser la propia raiz.
     */
    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    /**
     * @return la nueva raiz del subarbol despues de eliminar.
     *         El padre se encarga de reenlazar ese resultado.
     */
    private NodoArbol<Integer> eliminarRec(NodoArbol<Integer> nodo, int valor) {

        // Caso base: el valor no esta en el arbol.
        if (nodo == null) return null;

        // 1) Localizar el nodo.
        if (valor < nodo.getDato()) {
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), valor));

        } else if (valor > nodo.getDato()) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), valor));

        } else {
            // 2) Nodo encontrado: se aplica uno de los tres casos.

            // CASO 1 (hoja) y CASO 2 (un hijo, derecho):
            // si no hay hijo izquierdo se devuelve el derecho (null si es hoja).
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();

            // CASO 2 (un hijo, izquierdo).
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();

            // CASO 3 (dos hijos): sucesor inorden = minimo del subarbol derecho.
            int sucesor = minValor(nodo.getDerecho());

            // El nodo conserva su posicion y sus enlaces; solo cambia su dato.
            nodo.setDato(sucesor);

            // Se elimina el sucesor original del subarbol derecho
            // (asi no queda el valor duplicado). Ese sucesor nunca tiene hijo
            // izquierdo, por lo que cae en el CASO 1 o en el CASO 2.
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor));
        }
        return nodo;
    }

    /** Valor minimo de un subarbol: se baja siempre por la izquierda. */
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

    /** Valor maximo de un subarbol: se baja siempre por la derecha. */
    public int maxValor(NodoArbol<Integer> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("Subarbol vacio: no tiene maximo.");
        }
        NodoArbol<Integer> actual = nodo;
        while (actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }
        return actual.getDato();
    }

    /**
     * VARIANTE con antecesor inorden (maximo del subarbol izquierdo).
     *
     * OJO: con la convencion "iguales a la derecha" esta variante solo es
     * segura si las claves son UNICAS (como los codigos de producto).
     * Con duplicados puede dejar un valor igual al del nodo en su subarbol
     * izquierdo, lo que rompe la convencion. Por eso el metodo principal
     * eliminar() usa el sucesor.
     */
    public void eliminarConAntecesor(int valor) {
        raiz = eliminarAntRec(raiz, valor);
    }

    private NodoArbol<Integer> eliminarAntRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return null;

        if (valor < nodo.getDato()) {
            nodo.setIzquierdo(eliminarAntRec(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getDato()) {
            nodo.setDerecho(eliminarAntRec(nodo.getDerecho(), valor));
        } else {
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();

            int antecesor = maxValor(nodo.getIzquierdo());
            nodo.setDato(antecesor);
            nodo.setIzquierdo(eliminarAntRec(nodo.getIzquierdo(), antecesor));
        }
        return nodo;
    }

    // ==================================================================
    // VALIDACION Y UTILIDADES
    // ==================================================================

    /**
     * Verifica la propiedad BST en TODO el arbol (no solo padre-hijo):
     * cada nodo debe respetar los limites heredados de sus ancestros.
     *   izquierda: estrictamente menor  |  derecha: mayor o igual
     */
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

    /** Claves en inorden (deben salir ordenadas de menor a mayor). */
    public List<Integer> aLista() {
        List<Integer> lista = new ArrayList<>();
        inordenRec(raiz, lista);
        return lista;
    }

    private void inordenRec(NodoArbol<Integer> nodo, List<Integer> lista) {
        if (nodo == null) return;
        inordenRec(nodo.getIzquierdo(), lista);
        lista.add(nodo.getDato());
        inordenRec(nodo.getDerecho(), lista);
    }

    public String inorden() {
        StringBuilder sb = new StringBuilder();
        for (int v : aLista()) sb.append(v).append(' ');
        return sb.toString().trim();
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
