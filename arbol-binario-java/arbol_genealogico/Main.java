
public class Main {

    public static void main(String[] args) {

        // 1. Crear los siete nodos del arbol genealogico.
        NodoArbol<String> nicolas =
                new NodoArbol<>("Nicolas Gomez");

        NodoArbol<String> mileidy =
                new NodoArbol<>("Mileidy Villarreal - MAMA");

        NodoArbol<String> edgar =
                new NodoArbol<>("Edgar Gomez - PAPA");

        NodoArbol<String> barbara =
                new NodoArbol<>("Barbara Fierro - ABUELA MATERNA");

        NodoArbol<String> humberto =
                new NodoArbol<>("Humberto Villarreal - ABUELO MATERNO");

        NodoArbol<String> nohemi =
                new NodoArbol<>("Nohemi Gonzalez - ABUELA PATERNA");

        NodoArbol<String> belisario =
                new NodoArbol<>("Belisario Gomez - ABUELO PATERNO");

        // 2. Conectar los nodos.
        nicolas.setIzquierdo(mileidy);
        nicolas.setDerecho(edgar);

        mileidy.setIzquierdo(barbara);
        mileidy.setDerecho(humberto);

        edgar.setIzquierdo(nohemi);
        edgar.setDerecho(belisario);

        // 3. Crear el arbol.
        ArbolBinario<String> arbol =
                new ArbolBinario<>(nicolas);

        // 4. Imprimir la raiz y los hijos.
        arbol.imprimirRaizYHijos();

        // 5. Probar las referencias null.
        System.out.println("\n=== PRUEBA DE REFERENCIAS NULL ===");

        if (arbol.getRaiz() != null) {
            System.out.println("La raiz existe: "
                    + arbol.getRaiz().getDato());
        }

        if (barbara.getIzquierdo() == null
                && barbara.getDerecho() == null) {
            System.out.println(
                    "Barbara es una hoja y no tiene hijos.");
        }

        // 6. Probar un arbol vacio.
        System.out.println("\n=== PRUEBA DE ARBOL VACIO ===");

        ArbolBinario<String> arbolVacio =
                new ArbolBinario<>(null);

        arbolVacio.imprimirRaizYHijos();
    }
}
