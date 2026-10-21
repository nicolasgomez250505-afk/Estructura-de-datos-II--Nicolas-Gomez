# Reto: Droguería inteligente

Proyecto en Java para practicar Árboles Binarios de Búsqueda (BST), aplicado a un catálogo de productos de una droguería.

## Objetivos

- almacenar productos usando el código como clave;
- insertar de forma recursiva;
- buscar de forma recursiva;
- comparar un BST construido con datos aleatorios frente a uno construido con datos ordenados;
- calcular y comparar la altura.

## Compilar

```bash
javac Producto.java NodoProducto.java ResultadoBusqueda.java ArbolBSTCatalogo.java Main.java
```

## Ejecutar

```bash
java Main
```

## Regla BST

```text
código menor -> izquierda
código mayor -> derecha
```

## Convención de altura

- árbol vacío = -1
- hoja = 0
- altura = número máximo de aristas desde la raíz hasta una hoja
