public class ArbolBSTCatalogo {

    private NodoProducto raiz;

    // Inserción pública
    public void insertar(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null.");
        }
        raiz = insertarRec(raiz, producto);
    }

    // Inserción recursiva
    private NodoProducto insertarRec(NodoProducto nodo, Producto producto) {

        // Caso base: posición disponible.
        if (nodo == null) {
            return new NodoProducto(producto);
        }

        int codigoNuevo = producto.getCodigo();
        int codigoActual = nodo.getProducto().getCodigo();

        if (codigoNuevo < codigoActual) {
            nodo.setIzquierdo(
                    insertarRec(nodo.getIzquierdo(), producto)
            );
        } else if (codigoNuevo > codigoActual) {
            nodo.setDerecho(
                    insertarRec(nodo.getDerecho(), producto)
            );
        } else {
            // En un catálogo el código identifica de forma única al producto.
            throw new IllegalArgumentException(
                    "Código duplicado: " + codigoNuevo
            );
        }

        return nodo;
    }

    // Búsqueda pública
    public Producto buscar(int codigo) {
        return buscarRec(raiz, codigo);
    }

    // Búsqueda recursiva
    private Producto buscarRec(NodoProducto nodo, int codigo) {

        if (nodo == null) {
            return null;
        }

        int codigoActual = nodo.getProducto().getCodigo();

        if (codigo == codigoActual) {
            return nodo.getProducto();
        }

        if (codigo < codigoActual) {
            return buscarRec(nodo.getIzquierdo(), codigo);
        }

        return buscarRec(nodo.getDerecho(), codigo);
    }

    // Búsqueda didáctica: registra camino y comparaciones.
    public ResultadoBusqueda buscarConTraza(int codigo) {
        return buscarConTrazaRec(
                raiz, codigo, 0, new StringBuilder()
        );
    }

    private ResultadoBusqueda buscarConTrazaRec(
            NodoProducto nodo,
            int codigo,
            int comparaciones,
            StringBuilder camino) {

        if (nodo == null) {
            if (camino.length() > 0) camino.append(" -> null");
            else camino.append("null");

            return new ResultadoBusqueda(
                    null, comparaciones, camino.toString()
            );
        }

        comparaciones++;

        int codigoActual = nodo.getProducto().getCodigo();

        if (camino.length() > 0) camino.append(" -> ");
        camino.append(codigoActual);

        if (codigo == codigoActual) {
            return new ResultadoBusqueda(
                    nodo.getProducto(),
                    comparaciones,
                    camino.toString()
            );
        }

        if (codigo < codigoActual) {
            return buscarConTrazaRec(
                    nodo.getIzquierdo(),
                    codigo,
                    comparaciones,
                    camino
            );
        }

        return buscarConTrazaRec(
                nodo.getDerecho(),
                codigo,
                comparaciones,
                camino
        );
    }

    // Altura medida en aristas:
    // árbol vacío = -1, hoja = 0.
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(NodoProducto nodo) {
        if (nodo == null) {
            return -1;
        }

        int izq = alturaRec(nodo.getIzquierdo());
        int der = alturaRec(nodo.getDerecho());

        return 1 + Math.max(izq, der);
    }

    // Recorrido Inorden: muestra productos por código ascendente.
    public void mostrarInorden() {
        mostrarInordenRec(raiz);
    }

    private void mostrarInordenRec(NodoProducto nodo) {
        if (nodo == null) return;

        mostrarInordenRec(nodo.getIzquierdo());
        System.out.println(nodo.getProducto());
        mostrarInordenRec(nodo.getDerecho());
    }

    // Impresión lateral para observar la forma del árbol.
    public void imprimirArbol() {
        imprimirArbolRec(raiz, 0);
    }

    private void imprimirArbolRec(NodoProducto nodo, int nivel) {
        if (nodo == null) return;

        imprimirArbolRec(nodo.getDerecho(), nivel + 1);

        System.out.println(
                "    ".repeat(nivel)
                        + nodo.getProducto().getCodigo()
        );

        imprimirArbolRec(nodo.getIzquierdo(), nivel + 1);
    }
}
