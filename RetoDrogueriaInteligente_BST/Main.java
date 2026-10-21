import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        List<Producto> productos = crearProductos();

        // =====================================================
        // ESCENARIO 1: INSERCIÓN ALEATORIA
        // =====================================================
        List<Producto> aleatorios = new ArrayList<>(productos);

        // Semilla fija: permite repetir exactamente la práctica.
        Collections.shuffle(aleatorios, new Random(2024));

        ArbolBSTCatalogo bstAleatorio = new ArbolBSTCatalogo();

        for (Producto p : aleatorios) {
            bstAleatorio.insertar(p);
        }

        // =====================================================
        // ESCENARIO 2: INSERCIÓN ORDENADA
        // =====================================================
        List<Producto> ordenados = new ArrayList<>(productos);

        ordenados.sort(
                (a, b) -> Integer.compare(
                        a.getCodigo(),
                        b.getCodigo()
                )
        );

        ArbolBSTCatalogo bstOrdenado = new ArbolBSTCatalogo();

        for (Producto p : ordenados) {
            bstOrdenado.insertar(p);
        }

        // =====================================================
        // MOSTRAR ORDEN DE INSERCIÓN
        // =====================================================
        System.out.println("=== DROGUERÍA INTELIGENTE ===");

        System.out.println("\nOrden ALEATORIO:");
        imprimirCodigos(aleatorios);

        System.out.println("\nOrden ASCENDENTE:");
        imprimirCodigos(ordenados);

        // =====================================================
        // MOSTRAR LOS DOS ÁRBOLES
        // =====================================================
        System.out.println("\n=== BST ALEATORIO ===");
        bstAleatorio.imprimirArbol();
        System.out.println("Altura: " + bstAleatorio.altura());

        System.out.println("\n=== BST ORDENADO ===");
        bstOrdenado.imprimirArbol();
        System.out.println("Altura: " + bstOrdenado.altura());

        // =====================================================
        // BÚSQUEDAS
        // =====================================================
        int existente = 1160;
        int inexistente = 1100;

        System.out.println("\n=== BÚSQUEDAS EN BST ALEATORIO ===");
        mostrarBusqueda(bstAleatorio, existente);
        mostrarBusqueda(bstAleatorio, inexistente);

        System.out.println("\n=== BÚSQUEDAS EN BST ORDENADO ===");
        mostrarBusqueda(bstOrdenado, existente);
        mostrarBusqueda(bstOrdenado, inexistente);

        // =====================================================
        // COMPARACIÓN FINAL
        // =====================================================
        System.out.println("\n=== COMPARACIÓN FINAL ===");
        System.out.println(
                "Altura con datos aleatorios: "
                        + bstAleatorio.altura()
        );

        System.out.println(
                "Altura con datos ordenados: "
                        + bstOrdenado.altura()
        );

        System.out.println(
                "\nConclusión: el orden de inserción modifica "
                        + "la forma del BST. Cuando los datos llegan "
                        + "ordenados, el árbol puede degenerarse y "
                        + "aumentar considerablemente su altura."
        );

        // =====================================================
        // MOSTRAR CATÁLOGO ORDENADO POR CÓDIGO
        // =====================================================
        System.out.println("\n=== CATÁLOGO ORDENADO POR CÓDIGO ===");
        bstAleatorio.mostrarInorden();
    }

    private static void mostrarBusqueda(
            ArbolBSTCatalogo arbol,
            int codigo) {

        ResultadoBusqueda resultado =
                arbol.buscarConTraza(codigo);

        System.out.println("\nCódigo buscado: " + codigo);
        System.out.println(
                "Encontrado: " + resultado.encontrado()
        );
        System.out.println(
                "Comparaciones: " + resultado.getComparaciones()
        );
        System.out.println(
                "Camino: " + resultado.getCamino()
        );

        if (resultado.encontrado()) {
            System.out.println(
                    "Producto: " + resultado.getProducto()
            );
        }
    }

    private static void imprimirCodigos(
            List<Producto> productos) {

        for (Producto p : productos) {
            System.out.print(p.getCodigo() + " ");
        }
        System.out.println();
    }

    private static List<Producto> crearProductos() {

        List<Producto> lista = new ArrayList<>();

        lista.add(new Producto(1005, "Acetaminofén 500 mg",   "Analgésicos",        8500));
        lista.add(new Producto(1020, "Ibuprofeno 400 mg",      "Analgésicos",       12000));
        lista.add(new Producto(1035, "Diclofenaco gel",        "Analgésicos",       18500));
        lista.add(new Producto(1060, "Amoxicilina 500 mg",     "Antibióticos",      24000));
        lista.add(new Producto(1075, "Azitromicina 500 mg",    "Antibióticos",      31000));
        lista.add(new Producto(1090, "Loratadina 10 mg",       "Antialérgicos",      9500));
        lista.add(new Producto(1110, "Cetirizina 10 mg",       "Antialérgicos",     11000));
        lista.add(new Producto(1130, "Omeprazol 20 mg",        "Gastrointestinales", 15000));
        lista.add(new Producto(1145, "Sales de rehidratación", "Gastrointestinales",  4500));
        lista.add(new Producto(1160, "Vitamina C 1 g",         "Vitaminas",         22000));
        lista.add(new Producto(1180, "Multivitamínico",        "Vitaminas",         58000));
        lista.add(new Producto(1200, "Termómetro digital",     "Dispositivos",      35000));
        lista.add(new Producto(1225, "Tensiómetro",            "Dispositivos",     145000));
        lista.add(new Producto(1250, "Protector solar FPS 50", "Cuidado personal",  69000));
        lista.add(new Producto(1275, "Alcohol antiséptico",    "Cuidado personal",   7800));

        return lista;
    }
}
