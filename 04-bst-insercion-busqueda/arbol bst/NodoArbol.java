/**
 * Nodo de un Arbol Binario de Busqueda (generico).
 * Guarda un dato y dos referencias: hijo izquierdo y hijo derecho.
 */
public class NodoArbol<T> {

    private final T dato;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;

    public NodoArbol(T dato) {
        this.dato = dato;
    }

    public T getDato() { return dato; }

    public NodoArbol<T> getIzquierdo() { return izquierdo; }

    public NodoArbol<T> getDerecho() { return derecho; }

    public void setIzquierdo(NodoArbol<T> izquierdo) { this.izquierdo = izquierdo; }

    public void setDerecho(NodoArbol<T> derecho) { this.derecho = derecho; }
}
