# Guía 5 – BST II: eliminación de nodos

**Asignatura:** Estructura de Datos II  
**Programas:** Ingeniería de Software – Ingeniería de Sistemas · **Semestre:** IV  
**Ecosistema:** Java / GitHub (VS Code)

## Objetivo

Modificar un BST eliminando nodos sin romper su propiedad de ordenación.

El proyecto parte del BST de la Guía 4 (inserción, búsqueda, altura) y le agrega `eliminar()`, `minValor()` y un validador de la propiedad BST.

## Convención del BST

- clave **menor** que el nodo actual → subárbol **izquierdo**
- clave **mayor o igual** → subárbol **derecho**

## Estructura del proyecto

```text
05-bst-eliminacion/
├── src/
│   ├── NodoArbol.java          # ahora con setDato() (dato ya no es final)
│   ├── ArbolBST.java           # insertar, buscar, eliminar, minValor, esBSTValido
│   ├── ResultadoBusqueda.java  # (de la Guía 4)
│   ├── Main.java               # demostración de los tres casos + raíz
│   └── Pruebas.java            # pruebas automáticas
├── docs/
│   ├── diagrama_caso1_hoja.png
│   ├── diagrama_caso2_un_hijo.png
│   ├── diagrama_caso3_dos_hijos.png
│   ├── diagrama_eliminar_raiz.png
│   └── generar_diagramas.py    # regenera los PNG (matplotlib)
├── salida/
│   ├── salida_main.txt         # ejecución de Main
│   └── salida_pruebas.txt      # ejecución de Pruebas
└── README.md
```

## Cómo compilar y ejecutar

Desde la carpeta `05-bst-eliminacion`:

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main        # casos con árbol antes / después
java -cp out Pruebas     # pruebas automáticas (termina con código 1 si algo falla)
```

## Cambios respecto a la Guía 4

| Cambio | Motivo |
|---|---|
| `NodoArbol`: `dato` deja de ser `final` y se agrega `setDato()` | En el caso de dos hijos el nodo conserva su lugar y recibe el valor del sucesor (la guía lo indica en el comentario del código base). |
| `ArbolBST.eliminar()` / `eliminarRec()` | Eliminación recursiva con los tres casos. |
| `ArbolBST.minValor()` (y `maxValor()`) | Sucesor / antecesor inorden. |
| `ArbolBST.esBSTValido()` | Valida la propiedad BST en todo el árbol, no solo entre padre e hijo. |

## Los tres casos de eliminación

Una vez localizado el nodo, `eliminarRec` devuelve **la nueva raíz del subárbol**, y el padre reenlaza ese resultado con `setIzquierdo(...)` o `setDerecho(...)`. Por eso es importante que `eliminar()` haga `raiz = eliminarRec(raiz, valor)`: si el nodo eliminado es la raíz, la raíz del árbol cambia.

```java
} else {
    if (nodo.getIzquierdo() == null) return nodo.getDerecho();   // casos 1 y 2
    if (nodo.getDerecho() == null) return nodo.getIzquierdo();   // caso 2
    int sucesor = minValor(nodo.getDerecho());                   // caso 3
    nodo.setDato(sucesor);
    nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor));
}
return nodo;
```

### Caso 1 – Nodo hoja (eliminar `20`)

![Caso 1](docs/diagrama_caso1_hoja.png)

El nodo no tiene hijos, así que se devuelve `null`. **Referencia que cambia:** `30.izquierdo` pasa de `20` a `null`.

### Caso 2 – Nodo con un hijo (eliminar `60`, que tiene el hijo `65`)

![Caso 2](docs/diagrama_caso2_un_hijo.png)

Se devuelve el único hijo, y el padre apunta directamente a él, "saltándose" al nodo eliminado. **Referencia que cambia:** `70.izquierdo` pasa de `60` a `65`. Si el único hijo fuera el izquierdo, el razonamiento es idéntico (`return nodo.getIzquierdo()`).

### Caso 3 – Nodo con dos hijos (eliminar `30`)

![Caso 3](docs/diagrama_caso3_dos_hijos.png)

No se puede devolver ninguno de los dos hijos sin perder el otro. Se hace así:

1. Se busca el **sucesor inorden**: el mínimo del subárbol derecho (`minValor`), que es el valor inmediatamente mayor. Aquí es `40`.
2. Se **copia** ese valor en el nodo a eliminar (`setDato`). El nodo conserva su posición y sus dos enlaces; solo cambia su dato.
3. Se **elimina el sucesor original** del subárbol derecho. Como el mínimo nunca tiene hijo izquierdo, esa eliminación siempre cae en el caso 1 o en el caso 2.

**Referencias que cambian:** las del nodo no cambian; cambia la del padre del sucesor (aquí, `30.derecho` pasa de `40` a `null`). Cambia su **dato** (`30` → `40`).

### Ejercicio: eliminar la raíz y validar que siga ordenado

![Eliminar la raíz](docs/diagrama_eliminar_raiz.png)

Se eliminó `50` en un árbol donde el sucesor (`60`) tiene un hijo (`65`). El caso 3 termina usando internamente el caso 2: la raíz recibe `60`, y luego `70.izquierdo` pasa de `60` a `65`.

- Inorden antes: `20 30 40 50 60 65 70 80`
- Inorden después: `20 30 40 60 65 70 80` (sigue ordenado, sin el 50)
- `esBSTValido()` → `true`

### Resumen de referencias

| Caso | Qué se devuelve | Referencia que cambia | ¿Cambia el dato de algún nodo? |
|---|---|---|---|
| Hoja | `null` | la del padre → `null` | No |
| Un hijo | el único hijo | la del padre → ese hijo | No |
| Dos hijos | el mismo nodo | la del padre del sucesor (por la eliminación interna) | Sí: recibe el valor del sucesor |

## Pruebas de ejecución

`Pruebas.java` realiza **38 verificaciones** y todas pasan (ver [`salida/salida_pruebas.txt`](salida/salida_pruebas.txt)):

- Los tres casos, con el hijo único a la derecha y a la izquierda.
- Eliminar la raíz, incluyendo el caso en que el sucesor tiene un hijo.
- Bordes: árbol de un solo nodo, árbol vacío, valor inexistente, vaciar todo el árbol eliminando nodo por nodo (validando tras cada paso) y claves duplicadas.
- Dos pruebas aleatorias de 2000 operaciones (inserciones y eliminaciones mezcladas) comparadas contra una lista ordenada de referencia; tras cada operación el árbol debe ser válido y su inorden debe coincidir con la lista.

La salida de `Main` con los árboles antes y después de cada caso está en [`salida/salida_main.txt`](salida/salida_main.txt).

## Sucesor frente a antecesor

La guía permite usar el sucesor o el antecesor inorden. El método principal usa el **sucesor** y se incluye `eliminarConAntecesor()` como variante.

Con la convención "los iguales van a la derecha", el antecesor **solo es seguro con claves únicas** (como los códigos de producto). Con claves repetidas puede dejar en el subárbol izquierdo un valor igual al del nodo, lo que rompe la convención. Ejemplo reproducible en `Pruebas`: insertar `5, 3, 3, 8` y eliminar `5`:

| Variante | ¿BST válido después? | Inorden |
|---|---|---|
| Sucesor | Sí | `3 3 8` |
| Antecesor | **No** | `3 3 8` (el inorden sale igual, pero el `3` repetido queda a la izquierda del nodo que ahora vale `3`) |

Por eso el sucesor es la opción estable: funciona siempre con esta convención.

## Complejidad

Eliminar recorre un camino desde la raíz hasta el nodo y, en el caso de dos hijos, otro más hasta el mínimo del subárbol derecho. Ambos recorridos suman a lo sumo la altura `h` del árbol, así que el costo es **O(h)**: O(log n) en un árbol razonablemente balanceado y O(n) si se degenera (ver Guía 4).
