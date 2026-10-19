
public class EjemploArbolExpresion {

    public static void main(String[] args) {

        // 1. Crear los nodos de la expresion.
        NodoArbol<String> multiplicacion = new NodoArbol<>("*");
        NodoArbol<String> suma = new NodoArbol<>("+");
        NodoArbol<String> resta = new NodoArbol<>("-");

        NodoArbol<String> ocho = new NodoArbol<>("8");
        NodoArbol<String> cuatro = new NodoArbol<>("4");
        NodoArbol<String> nueve = new NodoArbol<>("9");
        NodoArbol<String> tres = new NodoArbol<>("3");

        // 2. Conectar los nodos.
        multiplicacion.setIzquierdo(suma);
        multiplicacion.setDerecho(resta);

        suma.setIzquierdo(ocho);
        suma.setDerecho(cuatro);

        resta.setIzquierdo(nueve);
        resta.setDerecho(tres);

        // 3. Mostrar la expresion.
        System.out.println("=== ARBOL DE EXPRESION ===");
        System.out.println("Expresion: (8 + 4) * (9 - 3)");

        // 4. Evaluar el arbol.
        double resultado = evaluar(multiplicacion);

        System.out.println("Resultado: " + resultado);
    }

    public static double evaluar(NodoArbol<String> nodo) {

        // Evitar acceder a una referencia inexistente.
        if (nodo == null) {
            throw new IllegalArgumentException(
                    "La expresion contiene un nodo null.");
        }

        String dato = nodo.getDato();

        // Si es una hoja, representa un numero.
        if (nodo.getIzquierdo() == null
                && nodo.getDerecho() == null) {
            return Double.parseDouble(dato);
        }

        // Un operador necesita sus dos operandos.
        if (nodo.getIzquierdo() == null
                || nodo.getDerecho() == null) {
            throw new IllegalArgumentException(
                    "El operador necesita dos hijos.");
        }

        double izquierdo = evaluar(nodo.getIzquierdo());
        double derecho = evaluar(nodo.getDerecho());

        switch (dato) {
            case "+":
                return izquierdo + derecho;

            case "-":
                return izquierdo - derecho;

            case "*":
                return izquierdo * derecho;

            case "/":
                if (derecho == 0) {
                    throw new ArithmeticException(
                            "No se puede dividir entre cero.");
                }
                return izquierdo / derecho;

            default:
                throw new IllegalArgumentException(
                        "Operador desconocido: " + dato);
        }
    }
}
