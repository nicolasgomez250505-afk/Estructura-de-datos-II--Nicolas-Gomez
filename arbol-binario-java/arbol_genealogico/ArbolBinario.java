
public class ArbolBinario<T> {

    private NodoArbol<T> raiz;

    public ArbolBinario(NodoArbol<T> raiz) {
        this.raiz = raiz;
    }

    public NodoArbol<T> getRaiz() {
        return raiz;
    }

    public void imprimirRaizYHijos() {
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }

        System.out.println("=== RAIZ DEL ARBOL ===");
        System.out.println("Raiz: " + raiz.getDato());

        imprimirHijos(raiz);
    }

    private void imprimirHijos(NodoArbol<T> nodo) {
        if (nodo == null) {
            return;
        }

        NodoArbol<T> izquierdo = nodo.getIzquierdo();
        NodoArbol<T> derecho = nodo.getDerecho();

        System.out.println("\nNodo: " + nodo.getDato());

        if (izquierdo != null) {
            System.out.println("  Hijo izquierdo: " + izquierdo.getDato());
        } else {
            System.out.println("  Hijo izquierdo: null (sin nodo)");
        }

        if (derecho != null) {
            System.out.println("  Hijo derecho: " + derecho.getDato());
        } else {
            System.out.println("  Hijo derecho: null (sin nodo)");
        }

        imprimirHijos(izquierdo);
        imprimirHijos(derecho);
    }
}
