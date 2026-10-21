public class Producto {
    private final int codigo;
    private final String nombre;
    private final String categoria;
    private final double precio;

    public Producto(int codigo, String nombre, String categoria, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - " + categoria
                + " - $" + String.format("%.2f", precio);
    }
}
