
public class Main {

    public static void main(String[] args) {

        // 1. CREACIÓN DE LOS NODOS
        NodoGeneral<String> videojuegos = new NodoGeneral<>("Videojuegos");

        // Nivel 1: categorías principales
        NodoGeneral<String> accion = new NodoGeneral<>("Acción");
        NodoGeneral<String> aventura = new NodoGeneral<>("Aventura");
        NodoGeneral<String> deportes = new NodoGeneral<>("Deportes");
        NodoGeneral<String> estrategia = new NodoGeneral<>("Estrategia");

        // Nivel 2: subcategorías
        NodoGeneral<String> disparos = new NodoGeneral<>("Disparos");
        NodoGeneral<String> lucha = new NodoGeneral<>("Lucha");
        NodoGeneral<String> mundoAbierto = new NodoGeneral<>("Mundo abierto");
        NodoGeneral<String> plataformas = new NodoGeneral<>("Plataformas");
        NodoGeneral<String> futbol = new NodoGeneral<>("Fútbol");
        NodoGeneral<String> carreras = new NodoGeneral<>("Carreras");
        NodoGeneral<String> tiempoReal = new NodoGeneral<>("Tiempo real");
        NodoGeneral<String> porTurnos = new NodoGeneral<>("Por turnos");

        // Nivel 3: videojuegos
        NodoGeneral<String> doom = new NodoGeneral<>("DOOM");
        NodoGeneral<String> gtaV = new NodoGeneral<>("Grand Theft Auto V");
        NodoGeneral<String> eaSportsFC = new NodoGeneral<>("EA Sports FC");
        NodoGeneral<String> ageOfEmpires = new NodoGeneral<>("Age of Empires");

        // 2. CONSTRUCCIÓN DE LA JERARQUÍA
        videojuegos.agregarHijo(accion);
        videojuegos.agregarHijo(aventura);
        videojuegos.agregarHijo(deportes);
        videojuegos.agregarHijo(estrategia);

        accion.agregarHijo(disparos);
        accion.agregarHijo(lucha);
        disparos.agregarHijo(doom);

        aventura.agregarHijo(mundoAbierto);
        aventura.agregarHijo(plataformas);
        mundoAbierto.agregarHijo(gtaV);

        deportes.agregarHijo(futbol);
        deportes.agregarHijo(carreras);
        futbol.agregarHijo(eaSportsFC);

        estrategia.agregarHijo(tiempoReal);
        estrategia.agregarHijo(porTurnos);
        tiempoReal.agregarHijo(ageOfEmpires);

        // 3. MOSTRAR EL ÁRBOL
        System.out.println("=== ÁRBOL DE VIDEOJUEGOS ===");
        MetricasArbol.mostrarArbol(videojuegos, 0);

        // 4. PROPIEDADES GENERALES
        System.out.println();
        System.out.println("=== PROPIEDADES DEL ÁRBOL ===");
        System.out.println("Raíz: " + videojuegos.getDato());
        System.out.println("Número total de nodos: "
                + MetricasArbol.contarNodos(videojuegos));
        System.out.println("Número de hojas: "
                + MetricasArbol.contarHojas(videojuegos));
        System.out.println("Grado del árbol: "
                + MetricasArbol.gradoArbol(videojuegos));
        System.out.println("Altura del árbol: "
                + MetricasArbol.altura(videojuegos));
        System.out.println("Número de niveles: "
                + (MetricasArbol.altura(videojuegos) + 1));

        // 5. PROFUNDIDAD DE CINCO NODOS
        System.out.println();
        System.out.println("=== PROFUNDIDAD DE CINCO NODOS ===");
        mostrarProfundidad(videojuegos, accion);
        mostrarProfundidad(videojuegos, mundoAbierto);
        mostrarProfundidad(videojuegos, doom);
        mostrarProfundidad(videojuegos, futbol);
        mostrarProfundidad(videojuegos, ageOfEmpires);

        // 6. PROFUNDIDAD DE TODAS LAS HOJAS
        System.out.println();
        System.out.println("=== PROFUNDIDAD DE TODAS LAS HOJAS ===");
        mostrarProfundidad(videojuegos, doom);
        mostrarProfundidad(videojuegos, lucha);
        mostrarProfundidad(videojuegos, gtaV);
        mostrarProfundidad(videojuegos, plataformas);
        mostrarProfundidad(videojuegos, eaSportsFC);
        mostrarProfundidad(videojuegos, carreras);
        mostrarProfundidad(videojuegos, ageOfEmpires);
        mostrarProfundidad(videojuegos, porTurnos);

        // 7. GRADO DE ALGUNOS NODOS
        System.out.println();
        System.out.println("=== GRADO DE ALGUNOS NODOS ===");
        mostrarGrado(videojuegos);
        mostrarGrado(accion);
        mostrarGrado(disparos);
        mostrarGrado(mundoAbierto);
        mostrarGrado(doom);

        // 8. TABLA COMPLETA DE MÉTRICAS
        System.out.println();
        System.out.println("=== TABLA DE MÉTRICAS ===");
        MetricasArbol.mostrarTablaMetricas(videojuegos);

        // 9. DOS CAMINOS Y SU LONGITUD
        System.out.println();
        System.out.println("=== CAMINOS ===");
        mostrarCamino(videojuegos, doom);
        mostrarCamino(videojuegos, ageOfEmpires);

        // 10. COMPARACIÓN MANUAL VS. CÓDIGO
        System.out.println();
        System.out.println("=== COMPARACIÓN ===");
        int alturaManual = 3;
        int alturaCalculada = MetricasArbol.altura(videojuegos);

        System.out.println("Altura calculada manualmente: " + alturaManual);
        System.out.println("Altura calculada por Java: " + alturaCalculada);
        System.out.println(alturaManual == alturaCalculada
                ? "Resultado: COINCIDEN."
                : "Resultado: REVISAR CÁLCULO.");

        // 11. AGREGAR UN NUEVO NIVEL Y COMPARAR LA ALTURA
        System.out.println();
        System.out.println("=== PRUEBA: AGREGAR UN NUEVO NIVEL ===");

        int alturaAnterior = MetricasArbol.altura(videojuegos);

        NodoGeneral<String> expansion =
                new NodoGeneral<>("Expansión");

        // Se agrega un hijo a DOOM, creando un nivel adicional.
        doom.agregarHijo(expansion);

        int alturaNueva = MetricasArbol.altura(videojuegos);

        System.out.println("Nodo agregado: Expansión");
        System.out.println("Padre del nuevo nodo: DOOM");
        System.out.println("Altura anterior: " + alturaAnterior);
        System.out.println("Altura nueva: " + alturaNueva);
        System.out.println("Niveles anteriores: " + (alturaAnterior + 1));
        System.out.println("Niveles nuevos: " + (alturaNueva + 1));

        if (alturaNueva == alturaAnterior + 1) {
            System.out.println(
                    "Resultado: la altura aumentó correctamente en 1.");
        } else {
            System.out.println(
                    "Resultado: revisar el cálculo de la altura.");
        }

        System.out.println();
        System.out.println("=== ÁRBOL MODIFICADO ===");
        MetricasArbol.mostrarArbol(videojuegos, 0);
    }

    private static void mostrarProfundidad(
            NodoGeneral<?> raiz, NodoGeneral<?> nodo) {
        System.out.printf("%-28s -> %d%n",
                nodo.getDato(), MetricasArbol.profundidad(raiz, nodo));
    }

    private static void mostrarGrado(NodoGeneral<?> nodo) {
        System.out.printf("%-28s -> %d%n",
                nodo.getDato(), MetricasArbol.gradoNodo(nodo));
    }

    private static void mostrarCamino(
            NodoGeneral<?> raiz, NodoGeneral<?> destino) {
        var ruta = MetricasArbol.camino(raiz, destino);
        System.out.println(MetricasArbol.rutaComoTexto(ruta));
        System.out.println("Longitud: "
                + MetricasArbol.longitudCamino(raiz, destino)
                + " aristas");
    }
}
