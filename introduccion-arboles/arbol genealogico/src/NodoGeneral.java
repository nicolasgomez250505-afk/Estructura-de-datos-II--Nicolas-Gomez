
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodoGeneral<T> {

    // Dato almacenado en el nodo.
    private final T dato;

    // Lista de hijos del nodo.
    private final List<NodoGeneral<T>> hijos;

    // Inicializa el nodo sin hijos.
    public NodoGeneral(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    // Agrega un hijo al nodo.
    public void agregarHijo(NodoGeneral<T> hijo) {
        if (hijo == null) {
            throw new IllegalArgumentException(
                "El nodo hijo no puede ser null."
            );
        }

        hijos.add(hijo);
    }

    // Devuelve el dato del nodo.
    public T getDato() {
        return dato;
    }

    // Devuelve los hijos sin permitir modificaciones externas.
    public List<NodoGeneral<T>> getHijos() {
        return Collections.unmodifiableList(hijos);
    }

    // Comprueba si el nodo no tiene hijos.
    public boolean esHoja() {
        return hijos.isEmpty();
    }

    // Muestra el árbol con sangría por nivel.
    public void mostrarJerarquia(int nivel) {
        String sangria = "    ".repeat(nivel);
        System.out.println(sangria + "- " + dato);

        // Recorre los hijos sin utilizar un for.
        for (NodoGeneral<T> hijo : hijos) {
            hijo.mostrarJerarquia(nivel + 1);
        }
    }

    public static void main(String[] args) {

        // 1. Crear la raíz del árbol.
        NodoGeneral<String> familia =
            new NodoGeneral<>("ARBOL GENEALOGICO");

        // 2. Crear los abuelos.
        NodoGeneral<String> abueloPaterno =
            new NodoGeneral<>("Belisario Gomez (Abuelo)");

        NodoGeneral<String> abuelaPaterna =
            new NodoGeneral<>("Nohemi Gonzalez (Abuela)");

        NodoGeneral<String> abuelaMaterna =
            new NodoGeneral<>("Barbara Fierro (Abuela)");

        NodoGeneral<String> abueloMaterno =
            new NodoGeneral<>("Humberto Villarreal (Abuelo)");

        // 3. Crear los padres.
        NodoGeneral<String> padre =
            new NodoGeneral<>("Edgar Alberto Gomez Gonzalez (Padre)");

        NodoGeneral<String> madre =
            new NodoGeneral<>("Blanca Mileidy Villarreal Fierro (Madre)");

        // 4. Crear los hijos.
        NodoGeneral<String> ana =
            new NodoGeneral<>("Ana Catalina Gomez Villarreal (Hermana)");

        NodoGeneral<String> nicolas =
            new NodoGeneral<>("Nicolas Alberto Gomez Villarreal (Yo)");

        NodoGeneral<String> laura =
            new NodoGeneral<>("Laura Valentina Gomez Villarreal (Hermana)");

        NodoGeneral<String> camilo =
            new NodoGeneral<>("Camilo Andres Gomez Villarreal (Hermano)");

        // 5. Agrupar los abuelos paternos.
        NodoGeneral<String> paternos =
            new NodoGeneral<>("Familia paterna");

        paternos.agregarHijo(abueloPaterno);
        paternos.agregarHijo(abuelaPaterna);
        paternos.agregarHijo(padre);

        // 6. Agrupar los abuelos maternos.
        NodoGeneral<String> maternos =
            new NodoGeneral<>("Familia materna");

        maternos.agregarHijo(abuelaMaterna);
        maternos.agregarHijo(abueloMaterno);
        maternos.agregarHijo(madre);

        // 7. Crear el grupo de los hijos.
        NodoGeneral<String> hijos =
            new NodoGeneral<>("Hijos de Edgar y Blanca");

        hijos.agregarHijo(ana);
        hijos.agregarHijo(nicolas);
        hijos.agregarHijo(laura);
        hijos.agregarHijo(camilo);

        // 8. Conectar las ramas a la raíz.
        familia.agregarHijo(paternos);
        familia.agregarHijo(maternos);
        familia.agregarHijo(hijos);

        // 9. Mostrar el árbol completo.
        System.out.println("ARBOL GENEALOGICO");
        System.out.println("=================");
        familia.mostrarJerarquia(0);

    }
}