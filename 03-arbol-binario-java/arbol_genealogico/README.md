
# Guía 3. Clasificación de árboles binarios y modelado en Java

**Asignatura:** Estructura de Datos II  
**Programa:** Ingeniería de Sistemas  
**Semestre:** IV  
**Lenguaje de programación:** Java  
**Repositorio:** Estructura de Datos II

---

## 1. Introducción

Los árboles binarios son estructuras de datos no lineales que permiten organizar información mediante relaciones entre nodos. Cada nodo puede tener como máximo dos hijos: un hijo izquierdo y un hijo derecho.

En esta actividad se estudia la clasificación de los árboles binarios, su implementación en Java mediante referencias de objetos y el manejo seguro de referencias `null`.

Como ejemplo principal se construye un árbol genealógico de siete nodos que representa a Nicolás Gómez, sus padres y sus cuatro abuelos. También se implementa un árbol de expresión matemática para representar y evaluar una operación.

## 2. Objetivos

- Comprender las características de los árboles binarios.
- Identificar los árboles llenos, completos, perfectos y degenerados.
- Implementar un nodo binario genérico en Java.
- Construir manualmente un árbol conectando referencias izquierda y derecha.
- Implementar un método para imprimir la raíz y los hijos de cada nodo.
- Evitar errores mediante la validación de referencias `null`.
- Representar una expresión matemática mediante un árbol binario y evaluar su resultado.

## 3. Conceptos fundamentales

### 3.1. Árbol binario

Es una estructura de datos en la que cada nodo tiene como máximo dos hijos. Estos se denominan hijo izquierdo e hijo derecho.

### 3.2. Nodo

Es cada elemento individual del árbol. Contiene un dato y referencias que permiten conectarlo con sus posibles hijos.

### 3.3. Raíz

Es el nodo principal del árbol. No tiene un nodo padre dentro de la estructura.

### 3.4. Hijo izquierdo e hijo derecho

Son las dos posibles referencias que puede almacenar cada nodo. La posición izquierda o derecha forma parte de la estructura y no necesariamente representa una relación familiar literal.

### 3.5. Hoja

Es un nodo que existe en el árbol, pero no tiene hijos. Sus referencias izquierda y derecha son `null`.

### 3.6. Referencia `null`

Representa la ausencia de un objeto en una referencia. Por ejemplo, si un nodo no tiene hijo izquierdo, su referencia izquierda puede ser `null`.

Es importante diferenciar ambos conceptos: una hoja es un nodo existente sin hijos; `null` indica que no existe un nodo conectado en esa posición.

### 3.7. Recursividad

Es una técnica mediante la cual un método se llama a sí mismo para resolver una tarea sobre partes más pequeñas de una estructura. En este proyecto se utiliza para recorrer el árbol y evaluar una expresión matemática.

## 4. Clasificación de árboles binarios

### 4.1. Árbol binario lleno

Un árbol binario lleno es aquel en el que cada nodo tiene cero o dos hijos. Ningún nodo tiene exactamente un hijo.

### 4.2. Árbol binario completo

Un árbol binario completo tiene todos sus niveles llenos, excepto posiblemente el último. Los nodos del último nivel se ubican de izquierda a derecha, sin dejar espacios intermedios.

### 4.3. Árbol binario perfecto

Un árbol binario perfecto tiene todos sus nodos internos con dos hijos y todas sus hojas ubicadas en el mismo nivel.

### 4.4. Árbol binario degenerado

Es un árbol en el que cada nodo tiene como máximo un hijo. Su estructura se parece a una lista enlazada.

### 4.5. Clasificación del árbol genealógico implementado

El árbol genealógico construido en Java es un árbol binario perfecto. La raíz tiene dos hijos, cada uno de los padres tiene dos hijos y las cuatro hojas están ubicadas en el mismo nivel.

Por cumplir estas condiciones, también es un árbol lleno y completo.

## 5. Diagrama del árbol genealógico

El árbol representa siete integrantes de la familia:

- **Raíz:** Nicolás Gómez.
- **Hijo izquierdo:** Mileidy Villarreal, mamá.
- **Hijo derecho:** Edgar Gómez, papá.
- **Hojas del lado materno:** Bárbara Fierro, abuela materna, y Humberto Villarreal, abuelo materno.
- **Hojas del lado paterno:** Nohemí González, abuela paterna, y Belisario Gómez, abuelo paterno.

### Relaciones de los nodos

| Nodo padre | Hijo izquierdo | Hijo derecho |
|---|---|---|
| Nicolás Gómez | Mileidy Villarreal | Edgar Gómez |
| Mileidy Villarreal | Bárbara Fierro | Humberto Villarreal |
| Edgar Gómez | Nohemí González | Belisario Gómez |

### Propiedades del árbol

- Número total de nodos: 7.
- Número de hojas: 4.
- Número de nodos internos: 3.
- Altura: 2 aristas.
- Número de niveles: 3.
- Máximo de hijos por nodo: 2.

**Nota sobre el modelo:** el árbol se orienta desde Nicolás hacia sus padres y luego hacia sus abuelos para cumplir el ejercicio de modelado binario. Es una representación adaptada al ejercicio de programación y no la orientación genealógica tradicional.

## 6. Implementación en Java

El proyecto está organizado en las siguientes clases.

### 6.1. `NodoArbol.java`

Define la estructura de cada nodo mediante un dato genérico y dos referencias: izquierda y derecha.

La clase utiliza el parámetro genérico `T`, que permite almacenar distintos tipos de datos. En este ejercicio se utiliza `String` para guardar los nombres y las etiquetas.

Sus métodos principales son:

- `getDato()`: obtiene el dato del nodo.
- `getIzquierdo()`: obtiene la referencia al hijo izquierdo.
- `getDerecho()`: obtiene la referencia al hijo derecho.
- `setIzquierdo()`: establece el hijo izquierdo.
- `setDerecho()`: establece el hijo derecho.

### 6.2. `ArbolBinario.java`

Administra la raíz del árbol y contiene el método `imprimirRaizYHijos()`.

El método verifica si la raíz es `null` antes de intentar acceder a ella. Después muestra el dato de la raíz y recorre los nodos para imprimir sus hijos.

La comprobación de referencias permite evitar accesos a objetos inexistentes y reducir el riesgo de producir una excepción `NullPointerException`.

### 6.3. `Main.java`

Es el programa principal del árbol genealógico. Sus responsabilidades son:

1. Crear los siete nodos.
2. Conectar los nodos mediante referencias izquierda y derecha.
3. Crear el objeto `ArbolBinario` utilizando a Nicolás Gómez como raíz.
4. Ejecutar el método `imprimirRaizYHijos()`.
5. Comprobar referencias `null`.
6. Probar el comportamiento de un árbol vacío.

### 6.4. `EjemploArbolExpresion.java`

Representa una operación matemática mediante un árbol binario y calcula su resultado.

La expresión utilizada es:

**(8 + 4) × (9 - 3)**

El nodo raíz contiene el operador de multiplicación. Su hijo izquierdo representa la suma y su hijo derecho representa la resta. Los números son las hojas del árbol.

El método recursivo `evaluar()` resuelve primero las operaciones de los hijos y después aplica el operador del nodo actual.

El resultado esperado es:

**(8 + 4) × (9 - 3) = 12 × 6 = 72**

La implementación también valida referencias `null`, operadores desconocidos, operadores con hijos faltantes y la división entre cero.

## 7. Manejo de referencias `null`

En Java, intentar llamar un método sobre una referencia `null` puede producir una excepción `NullPointerException`.

Para evitarlo, el proyecto utiliza comprobaciones antes de acceder a referencias. Por ejemplo, antes de recorrer los hijos de un nodo, se comprueba que el nodo exista.

También se realizan pruebas con un árbol vacío para verificar que el programa muestre un mensaje informativo en lugar de intentar acceder a una raíz inexistente.

En el árbol genealógico, las cuatro personas representadas como hojas no tienen hijos conectados. Por tanto, sus referencias izquierda y derecha son `null`.

En el árbol de expresiones, se verifica que los operadores tengan los dos hijos necesarios para evaluar la operación.

## 8. Ejecución del proyecto

Los programas se ejecutan por separado porque cada archivo principal tiene su propio método `main()`.

### Ejecución del árbol genealógico

Se ejecuta `Main.java`.

La salida debe mostrar la raíz, las relaciones de los siete nodos y las pruebas de referencias `null` y árbol vacío.

### Ejecución del árbol de expresiones

Se ejecuta `EjemploArbolExpresion.java`.

La salida esperada es:

```text
=== ARBOL DE EXPRESION ===
Expresion: (8 + 4) * (9 - 3)
Resultado: 72.0
```

## 9. Diagramas y evidencias

El proyecto debe incluir los diagramas de clasificación de árboles binarios y el diagrama del árbol genealógico.

También se deben conservar capturas de pantalla de las ejecuciones de los dos programas:

- Captura de `Main.java`, con el árbol genealógico y las pruebas.
- Captura de `EjemploArbolExpresion.java`, con el resultado de la expresión matemática.
- Imágenes de los árboles lleno, completo, perfecto y degenerado.
- Imagen del árbol genealógico elaborado en draw.io.

Las capturas deben corresponder a ejecuciones reales del programa.

## 10. Conclusiones

El desarrollo de esta actividad permitió comprender cómo se construye un árbol binario utilizando referencias a objetos en Java.

La implementación de `NodoArbol<T>` demuestra cómo una clase genérica puede representar nodos con un dato y dos posibles hijos. Por su parte, `ArbolBinario<T>` permite administrar la raíz y recorrer la estructura.

El árbol genealógico permitió practicar la conexión manual de siete nodos y reconocer la raíz, los nodos internos y las hojas. Además, las pruebas con `null` mostraron la importancia de validar las referencias antes de acceder a ellas.

Finalmente, el árbol de expresiones permitió aplicar la recursividad para evaluar una operación matemática y comprender cómo los operadores y los operandos se organizan dentro de una estructura binaria.
