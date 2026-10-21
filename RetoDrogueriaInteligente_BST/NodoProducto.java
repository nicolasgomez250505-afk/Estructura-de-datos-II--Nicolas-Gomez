public class NodoProducto {
    private Producto producto;
    private NodoProducto izquierdo;
    private NodoProducto derecho;

    public NodoProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getProducto() { return producto; }
    public NodoProducto getIzquierdo() { return izquierdo; }
    public NodoProducto getDerecho() { return derecho; }

    public void setIzquierdo(NodoProducto izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoProducto derecho) {
        this.derecho = derecho;
    }
}
