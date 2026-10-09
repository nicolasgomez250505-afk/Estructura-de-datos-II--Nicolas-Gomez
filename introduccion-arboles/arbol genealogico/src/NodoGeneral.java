
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Nodo general para construir estructuras jerárquicas.
 * Cada nodo almacena un dato y una lista de hijos.
 *
 * @param <T> tipo de dato almacenado en el nodo.
 */
public class NodoGeneral<T> {

    private final T dato;
    private final List<NodoGeneral<T>> hijos;

    public NodoGeneral(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(NodoGeneral<T> hijo) {
        if (hijo == null) {
            throw new IllegalArgumentException(
                "El nodo hijo no puede ser null."
            );
        }
        hijos.add(hijo);
    }

    public T getDato() {
        return dato;
    }

    public List<NodoGeneral<T>> getHijos() {
        return Collections.unmodifiableList(hijos);
    }

    public boolean esHoja() {
        return hijos.isEmpty();
    }

    /**
     * Muestra el nodo y sus descendientes en consola.
     */
    public void mostrarJerarquia(int nivel) {
        String sangria = " ".repeat(nivel);
        System.out.println(sangria + "- " + dato);

        for (NodoGeneral<T> hijo : hijos) {
            hijo.mostrarJerarquia(nivel + 1);
        }
    }

    public static void main(String[] args) {
        NodoGeneral<String> abuelo = new NodoGeneral<>("Abuelo");

        NodoGeneral<String> papa = new NodoGeneral<>("Papa");
        NodoGeneral<String> tio = new NodoGeneral<>("Tio");
        NodoGeneral<String> tia = new NodoGeneral<>("Tia");

        NodoGeneral<String> yo = new NodoGeneral<>("Yo");
        NodoGeneral<String> hermana = new NodoGeneral<>("Hermana");

        NodoGeneral<String> primo1 =
            new NodoGeneral<>("Primo Juan");
        NodoGeneral<String> primo2 =
            new NodoGeneral<>("Primo Pedro");
        NodoGeneral<String> prima =
            new NodoGeneral<>("Prima Ana");

        abuelo.agregarHijo(papa);
        abuelo.agregarHijo(tio);
        abuelo.agregarHijo(tia);

        papa.agregarHijo(yo);
        papa.agregarHijo(hermana);

        tio.agregarHijo(primo1);
        tio.agregarHijo(primo2);

        tia.agregarHijo(prima);

        System.out.println("Dato de la raiz: " + abuelo.getDato());
        System.out.println("Hijos directos de la raiz:");

        for (NodoGeneral<String> hijo : abuelo.getHijos()) {
            System.out.println(hijo.getDato());
        }

        System.out.println();
        System.out.println("Jerarquia completa:");
        abuelo.mostrarJerarquia(0);
    }
}