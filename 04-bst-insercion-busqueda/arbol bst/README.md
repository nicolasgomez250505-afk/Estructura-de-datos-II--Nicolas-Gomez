# Guía 4 – BST I: inserción y búsqueda

**Asignatura:** Estructura de Datos II  
**Programas:** Ingeniería de Software – Ingeniería de Sistemas · **Semestre:** IV  
**Ecosistema:** Java / GitHub (VS Code)

## Objetivo

Implementar inserción y búsqueda recursiva en un Árbol Binario de Búsqueda (BST) y analizar su eficiencia.

## Convención del BST

- clave **menor** que el nodo actual → subárbol **izquierdo**
- clave **mayor o igual** → subárbol **derecho**

## Estructura del proyecto

```text
04-bst-insercion-busqueda/
├── src/
│   ├── NodoArbol.java          # nodo genérico NodoArbol<T>
│   ├── ArbolBST.java           # insertar() y buscar() recursivos + utilidades
│   ├── ResultadoBusqueda.java  # evidencia: encontrado, comparaciones, camino
│   └── Main.java               # ejercicios y comparación aleatorio vs ordenado
├── docs/
│   ├── diagrama_ejercicio_7_nodos.png
│   ├── diagrama_insercion_aleatoria.png
│   ├── diagrama_insercion_ordenada.png
│   ├── comparacion_bst.csv
│   └── generar_diagramas.py    # regenera los PNG (matplotlib)
├── salida/salida_consola.txt   # salida real de la ejecución
└── README.md
```

## Cómo compilar y ejecutar

Desde la carpeta `04-bst-insercion-busqueda`:

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

## Implementación

`insertar()` y `buscar()` son recursivas y siguen un único camino desde la raíz:

- **Inserción:** si el nodo es `null` se crea el nuevo nodo (caso base); si no, se baja por la izquierda o la derecha y se reenlaza el hijo devuelto.
- **Búsqueda:** `null` → no existe; igual → encontrado; si no, se continúa por el lado que corresponda.
- `buscarConTraza()` hace la misma búsqueda registrando el **camino** y el **número de comparaciones** (una por nodo visitado).
- `altura()` se mide en **aristas** (árbol vacío = -1, hoja = 0).

## Ejercicios de la guía

### 1. Insertar 50, 30, 70, 20, 40, 60, 80 y dibujar el BST

![BST de 7 nodos](docs/diagrama_ejercicio_7_nodos.png)

Recorrido inorden: `20 30 40 50 60 70 80` (ordenado, lo que confirma la propiedad BST). Altura = 2.

### 2. Búsqueda de un valor existente y uno inexistente

| Búsqueda | Resultado | Comparaciones | Camino |
|---|---|---|---|
| `60` | encontrado | 3 | `50 -> 70 -> 60` |
| `65` | no encontrado | 3 | `50 -> 70 -> 60 -> null` |

### 3. ¿Por qué insertar datos ya ordenados degrada el rendimiento?

Si cada clave nueva es mayor que todas las anteriores, siempre se va a la derecha: cada nodo tiene un solo hijo y el árbol se convierte en una **lista enlazada** de altura `n - 1`. Como insertar y buscar recorren un camino de la raíz hasta el punto de destino, el costo pasa de proporcional a la altura de un árbol equilibrado (`≈ log n`) a proporcional a `n`.

## Experimento: 15 códigos de productos

Los mismos 15 códigos se insertan en dos órdenes distintos:

- **Aleatorio:** `3900, 1200, 7200, 5000, 900, 8500, 2300, 6100, 4500, 1800, 7800, 3100, 5600, 2700, 6700`
- **Ordenado:** `900, 1200, 1800, 2300, 2700, 3100, 3900, 4500, 5000, 5600, 6100, 6700, 7200, 7800, 8500`

Cinco búsquedas: `900`, `3100`, `6100`, `8500` (existentes) y `9999` (inexistente).

### Diagramas

**Inserción aleatoria** (altura 4)

![Inserción aleatoria](docs/diagrama_insercion_aleatoria.png)

**Inserción ordenada** (altura 14, degenerado)

![Inserción ordenada](docs/diagrama_insercion_ordenada.png)

### Comparaciones por búsqueda

| Código buscado | Comparaciones (aleatorio) | Comparaciones (ordenado) |
|---|---|---|
| 900 | 3 | 1 |
| 3100 | 4 | 6 |
| 6100 | 4 | 11 |
| 8500 | 3 | 15 |
| 9999 (no existe) | 3 | 15 |
| **Total** | **17** | **48** |

### Tabla comparativa

| Escenario | Nodos | Altura (aristas) | Comparaciones totales | Promedio en 5 búsquedas |
|---|---|---|---|---|
| Orden aleatorio | 15 | 4 | 17 | 3.4 |
| Orden ordenado | 15 | 14 | 48 | 9.6 |

(Datos también en [`docs/comparacion_bst.csv`](docs/comparacion_bst.csv) y salida completa en [`salida/salida_consola.txt`](salida/salida_consola.txt).)

> Nota: el código `900` es el más rápido en el árbol ordenado porque es la raíz. Esa ventaja desaparece para el resto de las claves: en el árbol degenerado el último código requiere recorrer los 15 nodos.

## Análisis: O(log n) frente a O(n)

El costo de insertar o buscar es proporcional a la **altura** `h` del árbol, porque se sigue un único camino.

| Caso | Forma del árbol | Altura | Costo |
|---|---|---|---|
| Razonablemente balanceado (datos en orden aleatorio) | ramas parejas | ≈ `log₂ n` | **O(log n)** |
| Degenerado (datos ya ordenados) | una sola rama | `n - 1` | **O(n)** |

Con `n = 15`, un árbol perfectamente balanceado tendría altura 3 (`⌊log₂ 15⌋`). El árbol aleatorio quedó en 4 (cerca del ideal) y el ordenado en 14 (el peor caso). El promedio de comparaciones pasó de 3.4 a 9.6; con miles de códigos la diferencia sería de decenas frente a miles de comparaciones.

**Conclusión:** el rendimiento de un BST depende de la forma del árbol, no solo de la cantidad de datos. Para evitar la degeneración existen los árboles auto-balanceados (AVL, rojo-negro), que se verán más adelante.
