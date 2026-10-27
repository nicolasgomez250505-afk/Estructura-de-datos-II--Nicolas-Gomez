# 🌳 Introducción a los Árboles en Java

## 1. Introducción

El día 26 de octubre de 2026 se realizó una actividad educativa dirigida a los estudiantes de primer semestre, con el objetivo de introducirlos al concepto de los **árboles en programación**, utilizando Java como lenguaje de referencia.

Durante la actividad se explicaron los conceptos básicos de las estructuras de datos tipo árbol. Posteriormente, los estudiantes realizaron un ejercicio práctico en el que debían construir el diagrama de un árbol, y finalmente participaron en un cuestionario interactivo mediante Kahoot para evaluar los conocimientos adquiridos.

La actividad buscó facilitar la comprensión de este tema mediante explicaciones teóricas, ejercicios visuales y una evaluación dinámica.

## 2. Objetivos de la actividad

### Objetivo general

Introducir a los estudiantes de primer semestre en el concepto de los árboles como estructuras de datos, comprendiendo su organización, sus componentes y su importancia dentro de la programación en Java.

### Objetivos específicos

- Comprender qué es un árbol en programación.
- Identificar los elementos principales de un árbol.
- Reconocer la relación entre padres, hijos, raíces y hojas.
- Representar gráficamente un árbol mediante un diagrama.
- Relacionar los conceptos teóricos con ejemplos prácticos.
- Evaluar los conocimientos aprendidos mediante un cuestionario en Kahoot.

## 3. ¿Qué es un árbol en programación?

Un **árbol** es una estructura de datos no lineal que permite organizar información de manera jerárquica. Esto significa que los datos se distribuyen en diferentes niveles y se relacionan entre sí mediante conexiones.

A diferencia de una estructura lineal, como un arreglo o una lista, en la que los elementos se organizan de manera secuencial, un árbol permite representar relaciones entre un elemento principal y otros elementos que dependen de él.

Los árboles se utilizan en diferentes áreas de la informática, como:

- Organización de archivos y carpetas.
- Representación de estructuras familiares.
- Organización de menús de aplicaciones.
- Sistemas de búsqueda y clasificación de datos.
- Representación de expresiones matemáticas.
- Desarrollo de algoritmos y estructuras de datos.

## 4. Conceptos básicos de los árboles

Para comprender cómo funciona un árbol, es necesario conocer sus componentes principales.

### 4.1. Nodo

Un nodo es cada uno de los elementos que forman parte del árbol. Puede almacenar un número, una palabra, un objeto u otro tipo de información.

### 4.2. Raíz

Es el nodo principal del árbol. Se encuentra en el nivel superior y no tiene un nodo padre dentro de ese árbol.

### 4.3. Padre

Es un nodo que tiene uno o más nodos conectados directamente por debajo de él.

### 4.4. Hijo

Es un nodo que depende directamente de otro nodo, conocido como su padre.

### 4.5. Hoja

Es un nodo que no tiene hijos. Representa uno de los extremos del árbol.

### 4.6. Arista o conexión

Es la relación que conecta dos nodos, normalmente un padre con uno de sus hijos.

### 4.7. Nivel

Indica la posición de un nodo dentro de la organización del árbol. La raíz suele ubicarse en el nivel 0, aunque algunas representaciones utilizan el nivel 1 como punto de partida.

### 4.8. Subárbol

Es una parte de un árbol que contiene un nodo y los descendientes de ese nodo.

## 5. Ejemplo de un árbol

El siguiente diagrama representa un árbol sencillo:

```text
             A
           /   \
          B     C
         / \     \
        D   E     F
```

En este ejemplo:

- **A** es la raíz del árbol.
- **B** y **C** son hijos de A.
- **A** es el padre de B y C.
- **D** y **E** son hijos de B.
- **F** es hijo de C.
- **D**, **E** y **F** son hojas porque no tienen hijos.
- Las líneas representan las conexiones entre los nodos.

Este ejemplo permite observar cómo se distribuye la información de manera jerárquica y cómo un mismo nodo puede tener varios hijos.

## 6. Los árboles en Java

Java permite implementar árboles mediante clases y objetos. Una forma básica de hacerlo consiste en crear una clase que represente cada nodo y que almacene su información y las referencias a sus hijos.

Por ejemplo:

```java
import java.util.ArrayList;
import java.util.List;

public class Nodo {
    String dato;
    List<Nodo> hijos;

    public Nodo(String dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(Nodo hijo) {
        hijos.add(hijo);
    }
}
```

### Explicación del código

- `class Nodo`: define una clase que representa un nodo del árbol.
- `String dato`: almacena la información del nodo como texto.
- `List<Nodo> hijos`: contiene la lista de nodos hijos.
- `ArrayList<>`: permite almacenar los hijos en una lista dinámica.
- `Nodo(String dato)`: es el constructor que inicializa un nodo.
- `agregarHijo(Nodo hijo)`: permite establecer una relación entre un nodo padre y uno de sus hijos.

Este código representa una estructura de árbol general, en la que un nodo puede tener cero, uno o varios hijos.

Es importante recordar que este es un ejemplo introductorio. En Java también existen implementaciones especializadas, como los árboles binarios y los árboles binarios de búsqueda.

## 7. Actividad práctica: construcción de un diagrama

Después de explicar los conceptos básicos, se propuso a los estudiantes construir gráficamente un árbol.

El propósito de este ejercicio fue que pudieran identificar visualmente los componentes explicados y comprender cómo se relacionan entre sí.

### Desarrollo de la actividad

1. Se explicó qué es un árbol y cómo se organiza.
2. Se identificó la raíz como punto de partida.
3. Se agregaron nodos hijos para representar las relaciones jerárquicas.
4. Se identificaron los padres, los hijos y las hojas.
5. Se revisó la estructura para comprobar que las conexiones fueran correctas.

La construcción del diagrama permitió transformar los conceptos teóricos en una representación visual, facilitando su comprensión.

## 8. Evaluación mediante Kahoot

Para finalizar la actividad, se realizó un cuestionario interactivo utilizando **Kahoot**, una plataforma que permite evaluar conocimientos mediante preguntas y respuestas.

El cuestionario tuvo como finalidad comprobar qué tanto habían comprendido los estudiantes después de la explicación y del ejercicio práctico.

La evaluación permitió reforzar los conceptos principales, identificar posibles dudas y fomentar la participación de los estudiantes mediante una dinámica interactiva.

Entre los temas que se podían evaluar se encuentran:

- Definición de árbol en programación.
- Identificación de la raíz.
- Diferencias entre padre e hijo.
- Reconocimiento de las hojas.
- Identificación de nodos y conexiones.
- Comprensión de la organización jerárquica.
- Aplicaciones de los árboles en informática.

## 9. Importancia de la actividad

La combinación de explicación teórica, construcción de diagramas y evaluación interactiva permitió abordar el tema desde diferentes perspectivas.

El ejercicio gráfico ayudó a visualizar la estructura de los árboles, mientras que el cuestionario permitió repasar los conceptos y comprobar su comprensión.

Este tipo de actividades contribuye al desarrollo del pensamiento lógico y sirve como base para estudiar estructuras de datos más avanzadas en Java.

## 10. Conclusiones

La actividad permitió introducir a los estudiantes de primer semestre en los fundamentos de los árboles como estructuras de datos utilizadas en programación.

A través de la explicación de los conceptos básicos, la construcción de un diagrama y la evaluación mediante Kahoot, se buscó fortalecer la comprensión de las relaciones jerárquicas entre los nodos.

El conocimiento de estas estructuras constituye una base importante para continuar aprendiendo sobre programación, algoritmos y estructuras de datos en Java.

## 11. Tecnologías y herramientas utilizadas

- **Java:** lenguaje de programación utilizado como referencia para explicar la implementación de nodos y árboles.
- **Diagramas:** recurso visual para representar la estructura y las relaciones entre los nodos.
- **Kahoot:** plataforma interactiva utilizada para evaluar los conocimientos adquiridos.

---

**Tema:** Introducción a los árboles en Java  
**Público:** Estudiantes de primer semestre  
**Fecha de la actividad:** 26 de octubre de 2026