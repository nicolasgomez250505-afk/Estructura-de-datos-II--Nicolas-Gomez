/**
 * Nodo de un Arbol Binario de Busqueda (generico).
 *
 * Cambio respecto a la Guia 4: el dato YA NO es final y se agrega setDato().
 * Es necesario para el caso de eliminacion con dos hijos, donde el nodo
 * conserva su posicion y recibe el valor de su sucesor inorden.
 */
public class NodoArbol<T> {

    private T dato;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;

    public NodoArbol(T dato) {
        this.dato = dato;
    }

    public T getDato() { return dato; }

    public void setDato(T dato) { this.dato = dato; }

    public NodoArbol<T> getIzquierdo() { return izquierdo; }

    public NodoArbol<T> getDerecho() { return derecho; }

    public void setIzquierdo(NodoArbol<T> izquierdo) { this.izquierdo = izquierdo; }

    public void setDerecho(NodoArbol<T> derecho) { this.derecho = derecho; }
}
