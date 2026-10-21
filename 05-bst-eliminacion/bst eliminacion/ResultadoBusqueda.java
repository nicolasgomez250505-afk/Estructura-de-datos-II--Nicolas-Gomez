/**
 * Evidencia de una busqueda: si encontro el valor, cuantas comparaciones
 * hizo (nodos visitados) y el camino recorrido desde la raiz.
 */
public class ResultadoBusqueda {

    private final int valor;
    private final boolean encontrado;
    private final int comparaciones;
    private final String camino;

    public ResultadoBusqueda(int valor, boolean encontrado, int comparaciones, String camino) {
        this.valor = valor;
        this.encontrado = encontrado;
        this.comparaciones = comparaciones;
        this.camino = camino;
    }

    public int getValor() { return valor; }

    public boolean isEncontrado() { return encontrado; }

    public int getComparaciones() { return comparaciones; }

    public String getCamino() { return camino; }

    @Override
    public String toString() {
        return "Buscar " + valor
                + " | encontrado=" + encontrado
                + " | comparaciones=" + comparaciones
                + " | camino: " + camino;
    }
}
