# Tabla comparativa: BST con datos aleatorios vs. datos ordenados

Catálogo: 15 productos de droguería (códigos 1005 a 1275).
Semilla del orden aleatorio: `new Random(2024)`.
Convención de altura: árbol vacío = -1, hoja = 0 (altura en aristas).

| Criterio                                | BST aleatorio | BST ordenado |
|-----------------------------------------|:-------------:|:------------:|
| Orden de inserción                      | 1075, 1110, 1060, 1250, 1020, ... | 1005, 1020, 1035, 1060, ... |
| Número de productos (n)                 | 15            | 15           |
| Altura obtenida                         | 7             | 14           |
| Altura mínima posible con n = 15        | 3             | 3            |
| Forma del árbol                         | Ramificado    | Degenerado (lista inclinada a la derecha) |
| Búsqueda de 1160 (existe): comparaciones| 7             | 10           |
| Búsqueda de 1100 (no existe): comparaciones | 3         | 7            |
| Complejidad de búsqueda (caso típico)   | O(log n) aprox. | O(n)       |

## Análisis

- Con datos ordenados cada código nuevo es mayor que todos los anteriores, así que siempre se inserta a la derecha. El árbol queda como una lista enlazada y su altura es n - 1 = 14.
- Con datos aleatorios los códigos se reparten a ambos lados y el árbol queda más bajo (altura 7), por lo que se necesitan menos comparaciones para buscar.
- La diferencia crece con n: en un catálogo de miles de productos, un BST degenerado haría búsquedas lineales, mientras que uno equilibrado haría unas pocas decenas de comparaciones.
- Conclusión: el BST simple no se autoequilibra; su rendimiento depende del orden de inserción. Para evitar el peor caso se usan árboles balanceados (AVL, rojo-negro).
