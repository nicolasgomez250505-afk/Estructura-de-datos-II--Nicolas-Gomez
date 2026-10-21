import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Pruebas automaticas de la eliminacion (sin librerias externas).
 * Cada verificacion imprime [OK] o [FALLO]; si algo falla el programa
 * termina con codigo de salida 1.
 */
public class Pruebas {

    private static int total = 0;
    private static int fallos = 0;

    public static void main(String[] args) {

        System.out.println("=== PRUEBAS DE CASOS BASICOS ===");
        pruebaHoja();
        pruebaUnHijoDerecho();
        pruebaUnHijoIzquierdo();
        pruebaDosHijos();
        pruebaRaiz();
        pruebaRaizConSucesorConHijo();

        System.out.println("\n=== PRUEBAS DE BORDE ===");
        pruebaArbolConUnSoloNodo();
        pruebaArbolVacio();
        pruebaValorInexistente();
        pruebaVaciarTodo();
        pruebaDuplicados();

        System.out.println("\n=== VARIANTE CON ANTECESOR ===");
        pruebaAntecesor();
        demostracionAntecesorConDuplicados();

        System.out.println("\n=== PRUEBAS ALEATORIAS (contra una lista de referencia) ===");
        estresSucesorConDuplicados();
        estresAntecesorClavesUnicas();

        System.out.println("\n----------------------------------------");
        System.out.println("Verificaciones: " + total + " | Fallos: " + fallos);
        System.out.println(fallos == 0 ? "RESULTADO: TODAS LAS PRUEBAS PASARON"
                                       : "RESULTADO: HAY FALLOS");
        if (fallos > 0) System.exit(1);
    }

    // ---------------------------------------------------------------
    // Casos basicos
    // ---------------------------------------------------------------

    private static void pruebaHoja() {
        ArbolBST a = base();
        a.eliminar(20);
        check("Hoja: inorden sin el 20", a.inorden().equals("30 40 50 60 70 80"));
        check("Hoja: el padre (30) ya no tiene hijo izquierdo",
                a.getRaiz().getIzquierdo().getIzquierdo() == null);
        check("Hoja: el 20 ya no existe", !a.buscar(20));
        check("Hoja: BST valido", a.esBSTValido());
    }

    private static void pruebaUnHijoDerecho() {
        ArbolBST a = base();
        a.insertar(65);                                   // 60 -> hijo derecho 65
        a.eliminar(60);
        check("Un hijo (der): 70 apunta directo a 65",
                a.getRaiz().getDerecho().getIzquierdo().getDato() == 65);
        check("Un hijo (der): inorden correcto",
                a.inorden().equals("20 30 40 50 65 70 80"));
        check("Un hijo (der): BST valido", a.esBSTValido());
    }

    private static void pruebaUnHijoIzquierdo() {
        ArbolBST a = base();
        a.insertar(35);                                   // 40 -> hijo izquierdo 35
        a.eliminar(40);
        check("Un hijo (izq): 30 apunta directo a 35",
                a.getRaiz().getIzquierdo().getDerecho().getDato() == 35);
        check("Un hijo (izq): inorden correcto",
                a.inorden().equals("20 30 35 50 60 70 80"));
        check("Un hijo (izq): BST valido", a.esBSTValido());
    }

    private static void pruebaDosHijos() {
        ArbolBST a = base();
        a.eliminar(30);
        check("Dos hijos: el nodo conserva su lugar con el valor del sucesor (40)",
                a.getRaiz().getIzquierdo().getDato() == 40);
        check("Dos hijos: el hijo izquierdo sigue siendo 20",
                a.getRaiz().getIzquierdo().getIzquierdo().getDato() == 20);
        check("Dos hijos: el 40 original se elimino (sin duplicados)",
                a.getRaiz().getIzquierdo().getDerecho() == null);
        check("Dos hijos: inorden correcto", a.inorden().equals("20 40 50 60 70 80"));
        check("Dos hijos: BST valido", a.esBSTValido());
    }

    private static void pruebaRaiz() {
        ArbolBST a = base();
        a.eliminar(50);
        check("Raiz: la nueva raiz es el sucesor (60)", a.getRaiz().getDato() == 60);
        check("Raiz: inorden correcto", a.inorden().equals("20 30 40 60 70 80"));
        check("Raiz: BST valido", a.esBSTValido());
        check("Raiz: el 50 ya no existe", !a.buscar(50));
    }

    private static void pruebaRaizConSucesorConHijo() {
        ArbolBST a = base();
        a.insertar(65);
        a.eliminar(50);
        check("Raiz + sucesor con hijo: nueva raiz 60", a.getRaiz().getDato() == 60);
        check("Raiz + sucesor con hijo: 70 queda con hijo izquierdo 65",
                a.getRaiz().getDerecho().getIzquierdo().getDato() == 65);
        check("Raiz + sucesor con hijo: inorden correcto",
                a.inorden().equals("20 30 40 60 65 70 80"));
        check("Raiz + sucesor con hijo: BST valido", a.esBSTValido());
    }

    // ---------------------------------------------------------------
    // Casos de borde
    // ---------------------------------------------------------------

    private static void pruebaArbolConUnSoloNodo() {
        ArbolBST a = new ArbolBST();
        a.insertar(10);
        a.eliminar(10);
        check("Un solo nodo: la raiz queda en null", a.getRaiz() == null);
        check("Un solo nodo: 0 nodos", a.contarNodos() == 0);
    }

    private static void pruebaArbolVacio() {
        ArbolBST a = new ArbolBST();
        a.eliminar(5);
        check("Arbol vacio: eliminar no falla y sigue vacio", a.getRaiz() == null);
    }

    private static void pruebaValorInexistente() {
        ArbolBST a = base();
        a.eliminar(999);
        check("Inexistente: no cambia el inorden", a.inorden().equals("20 30 40 50 60 70 80"));
        check("Inexistente: sigue con 7 nodos", a.contarNodos() == 7);
    }

    private static void pruebaVaciarTodo() {
        ArbolBST a = base();
        int[] orden = {50, 20, 70, 30, 80, 40, 60};
        boolean todoValido = true;
        for (int v : orden) {
            a.eliminar(v);
            todoValido &= a.esBSTValido();
        }
        check("Vaciar todo: BST valido tras cada eliminacion", todoValido);
        check("Vaciar todo: el arbol queda vacio", a.getRaiz() == null);
    }

    private static void pruebaDuplicados() {
        ArbolBST a = new ArbolBST();
        for (int v : new int[]{50, 50, 50, 30, 70, 50}) a.insertar(v);
        boolean todoValido = true;
        a.eliminar(50);
        todoValido &= a.esBSTValido();
        a.eliminar(50);
        todoValido &= a.esBSTValido();
        check("Duplicados: BST valido tras eliminar 50 dos veces", todoValido);
        check("Duplicados: se elimina una ocurrencia por llamada",
                a.inorden().equals("30 50 50 70"));
    }

    private static void pruebaAntecesor() {
        ArbolBST a = base();
        a.eliminarConAntecesor(50);
        check("Antecesor: la nueva raiz es 40", a.getRaiz().getDato() == 40);
        check("Antecesor: inorden correcto", a.inorden().equals("20 30 40 60 70 80"));
        check("Antecesor: BST valido", a.esBSTValido());
    }

    /**
     * Demostracion (no cuenta como prueba): con duplicados, el antecesor puede
     * dejar un valor igual en el subarbol izquierdo y romper la convencion.
     * El sucesor, en cambio, funciona con la misma entrada.
     */
    private static void demostracionAntecesorConDuplicados() {
        ArbolBST conAntecesor = new ArbolBST();
        ArbolBST conSucesor = new ArbolBST();
        for (int v : new int[]{5, 3, 3, 8}) {
            conAntecesor.insertar(v);
            conSucesor.insertar(v);
        }
        conAntecesor.eliminarConAntecesor(5);
        conSucesor.eliminar(5);
        System.out.println("[INFO]  Claves {5,3,3,8}, eliminar 5 -> antecesor valido: "
                + conAntecesor.esBSTValido() + " | sucesor valido: " + conSucesor.esBSTValido());
        check("Sucesor es estable con duplicados en el caso {5,3,3,8}", conSucesor.esBSTValido());
    }

    // ---------------------------------------------------------------
    // Pruebas aleatorias
    // ---------------------------------------------------------------

    /** Mezcla inserciones y eliminaciones (con duplicados) y compara con una lista. */
    private static void estresSucesorConDuplicados() {
        Random rnd = new Random(7);
        ArbolBST arbol = new ArbolBST();
        List<Integer> ref = new ArrayList<>();
        boolean ok = true;

        for (int i = 0; i < 2000 && ok; i++) {
            int v = rnd.nextInt(40);
            if (rnd.nextBoolean()) {
                arbol.insertar(v);
                ref.add(v);
            } else {
                arbol.eliminar(v);
                ref.remove(Integer.valueOf(v));          // quita UNA ocurrencia
            }
            Collections.sort(ref);
            ok = arbol.esBSTValido() && arbol.aLista().equals(ref);
        }
        check("Aleatorio (sucesor, con duplicados): 2000 operaciones coherentes", ok);
    }

    /** Igual, pero con claves unicas y la variante del antecesor. */
    private static void estresAntecesorClavesUnicas() {
        Random rnd = new Random(11);
        ArbolBST arbol = new ArbolBST();
        List<Integer> ref = new ArrayList<>();
        boolean ok = true;

        for (int i = 0; i < 2000 && ok; i++) {
            int v = rnd.nextInt(60);
            if (rnd.nextBoolean()) {
                if (!ref.contains(v)) {                  // claves unicas
                    arbol.insertar(v);
                    ref.add(v);
                }
            } else {
                arbol.eliminarConAntecesor(v);
                ref.remove(Integer.valueOf(v));
            }
            Collections.sort(ref);
            ok = arbol.esBSTValido() && arbol.aLista().equals(ref);
        }
        check("Aleatorio (antecesor, claves unicas): 2000 operaciones coherentes", ok);
    }

    // ---------------------------------------------------------------

    private static ArbolBST base() {
        ArbolBST a = new ArbolBST();
        for (int v : Arrays.asList(50, 30, 70, 20, 40, 60, 80)) a.insertar(v);
        return a;
    }

    private static void check(String nombre, boolean condicion) {
        total++;
        if (!condicion) fallos++;
        System.out.println((condicion ? "[OK]    " : "[FALLO] ") + nombre);
    }
}
