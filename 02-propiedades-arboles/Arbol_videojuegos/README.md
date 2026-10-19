# Guía 2 – Anatomía y propiedades de un árbol

NICOLAS ALBERTO GOMEZ VILLARREAL

SERGIO VALENCIA PIRE

**Asignatura:** Estructura de Datos II  
**Programa:** Ingeniería de Sistemas  
**Semestre:** IV  
**Ecosistema:** Java / GitHub

## 1. Objetivo

Identificar, representar y calcular las propiedades de un árbol general mediante Java, aplicando algoritmos recursivos para analizar sus nodos, relaciones, grados, niveles, profundidades, alturas y caminos.

## 2. Descripción del proyecto

Este proyecto representa una clasificación jerárquica de videojuegos por género y subgénero. La estructura permite observar cómo un nodo puede tener varios hijos y cómo los nodos se organizan desde una raíz hasta sus hojas. El programa calcula diferentes métricas del árbol y muestra los resultados por consola.

## 3. Árbol utilizado

```text
Videojuegos (raíz)
├── Acción
│   ├── Disparos
│   │   └── DOOM
│   └── Lucha
├── Aventura
│   ├── Mundo abierto
│   │   └── Grand Theft Auto V
│   └── Plataformas
├── Deportes
│   ├── Fútbol
│   │   └── EA Sports FC
│   └── Carreras
└── Estrategia
    ├── Tiempo real
    │   └── Age of Empires
    └── Por turnos
```

El árbol contiene **17 nodos**, tiene un **grado 4**, una **altura 3** y **4 niveles**. La raíz es Videojuegos.

## 4. Convenciones utilizadas

- **Raíz:** nodo principal del árbol, en este caso, Videojuegos.
- **Hoja:** nodo que no tiene hijos.
- **Nodo interno:** nodo que tiene uno o más hijos.
- **Grado de un nodo:** cantidad de hijos directos.
- **Grado del árbol:** mayor grado encontrado entre todos sus nodos.
- **Profundidad:** distancia desde la raíz hasta un nodo, medida en número de aristas. La raíz tiene profundidad 0.
- **Nivel:** se considera igual a la profundidad, según las convenciones de la guía.
- **Altura de un nodo:** longitud del camino más largo desde ese nodo hasta una hoja, medida en aristas. Una hoja tiene altura 0.
- **Altura del árbol:** altura de la raíz, equivalente al máximo número de aristas desde la raíz hasta una hoja.
- **Camino:** secuencia de nodos conectados desde la raíz hasta un nodo de destino.
- **Longitud del camino:** cantidad de aristas que componen el camino.

## 5. Estructura de los archivos

- **`NodoGeneral.java`:** define la estructura de un nodo genérico, almacena su dato y sus hijos, permite agregar hijos y consultar si es hoja o conocer su grado.
- **`MetricasArbol.java`:** contiene los métodos para calcular recursivamente la altura, profundidad, grado del árbol, cantidad de nodos y hojas. También encuentra caminos, calcula sus longitudes, muestra el árbol y genera la tabla de métricas.
- **`Main.java`:** construye el árbol de videojuegos, establece las relaciones entre padres e hijos, ejecuta los métodos y muestra los resultados.

## 6. Propiedades del árbol

### Número de nodos

El árbol contiene 17 nodos en total, incluyendo la raíz, los nodos internos y las hojas.

### Grado de un nodo

Es el número de hijos directos. Por ejemplo, Videojuegos tiene grado 4 porque posee cuatro hijos: Acción, Aventura, Deportes y Estrategia.

### Grado del árbol

Es el mayor grado entre todos los nodos. En este caso, el grado del árbol es **4**, correspondiente a la raíz Videojuegos.

### Hojas

Son los nodos que no tienen hijos. En el árbol hay 8 hojas: DOOM, Lucha, Grand Theft Auto V, Plataformas, EA Sports FC, Carreras, Age of Empires y Por turnos.

### Altura

La altura se calcula usando el camino más largo desde la raíz hasta una hoja. Como una hoja tiene altura 0, la altura del árbol es **3 aristas**.

```text
altura(nodo) = 1 + máxima altura de sus hijos
```

### Profundidad y nivel

La profundidad se obtiene contando las aristas desde Videojuegos hasta el nodo seleccionado. La raíz tiene profundidad 0 y el nivel se toma igual a la profundidad.

## 7. Ejercicios desarrollados

1. Calcular la profundidad de cinco nodos seleccionados.
2. Mostrar el grado de diferentes nodos del árbol.
3. Generar una tabla con el grado, nivel, profundidad y altura de cada nodo.
4. Encontrar dos caminos distintos desde la raíz y calcular sus longitudes.
5. Comparar la altura calculada manualmente con la obtenida mediante Java.

## 8. Reto: agregar un nuevo nivel

Para analizar cómo cambia la altura, se puede agregar un nuevo nodo como hijo de una hoja que se encuentre en el camino de mayor longitud. Por ejemplo, agregar un nodo llamado `Expansión` como hijo de `DOOM`.

Antes de agregarlo:

- Altura del árbol: 3.
- Número de niveles: 4.

Después de agregarlo:

- Altura del árbol: 4.
- Número de niveles: 5.

La altura aumenta porque se agrega una arista al camino más largo desde la raíz hasta una hoja.

## 9. Compilación y ejecución

Se necesita tener instalado el JDK de Java y abrir una terminal en la carpeta que contiene los archivos del proyecto.

Compilar:

```bash
javac NodoGeneral.java MetricasArbol.java Main.java
```

Ejecutar:

```bash
java Main
```

El programa muestra el árbol, sus propiedades generales, las profundidades seleccionadas, el grado de algunos nodos, la tabla completa de métricas, dos caminos y la comparación entre la altura manual y la calculada por Java.

## 10. Resultado esperado

Los resultados deben coincidir con la estructura del árbol de videojuegos y las convenciones definidas en la guía. La altura calculada por Java debe coincidir con el cálculo manual cuando se utiliza la misma estructura.

## 11. Conclusión

Este proyecto permite comprender la representación de árboles generales en Java y el cálculo de sus propiedades mediante algoritmos recursivos. La separación entre las clases `NodoGeneral`, `MetricasArbol` y `Main` facilita la organización, reutilización y verificación del código.
