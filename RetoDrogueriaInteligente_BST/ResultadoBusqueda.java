public class ResultadoBusqueda {
    private final Producto producto;
    private final int comparaciones;
    private final String camino;

    public ResultadoBusqueda(Producto producto, int comparaciones, String camino) {
        this.producto = producto;
        this.comparaciones = comparaciones;
        this.camino = camino;
    }

    public boolean encontrado() { return producto != null; }
    public Producto getProducto() { return producto; }
    public int getComparaciones() { return comparaciones; }
    public String getCamino() { return camino; }
}
