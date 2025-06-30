package uso;

import imple.Cola;
import imple.Conjunto;
import imple.Pila;
import tda.*;
import tda.ABBTDA;
import tda.ConjuntoTDA;

public class Funciones {
    public static void main(String[] args) {
        Funciones funciones = new Funciones();
    }

    /**
     * Ejercicio 6:
     * <p>
     * Se define un método que reciba una PilaTDA y devuelva un float (número real)
     * con el porcentaje de cantidad de elementos pares de la pila.
     * <p>
     * Complejidad Lineal
     */
    private float porcentajeDeElementosParesEnPila(PilaTDA pila) {
        if (pila.pilaVacia()) {
            return 0.0f; // Si la pila está vacía, el porcentaje es 0
        }

        PilaTDA aux = new Pila();
        aux.inicializarPila();
        float cantidadPares = 0;
        float cantidadElementos = 0;
        // Recorremos la pila y contamos los elementos pares
        // y la cantidad total de elementos.
        // Almacenandolos en una pila auxiliar para poder restaurar la original.
        while (!pila.pilaVacia()) {
            int elemento = pila.tope();
            pila.desapilar();
            if (elemento % 2 == 0) {
                cantidadPares++;
            }
            cantidadElementos++;
            aux.apilar(elemento);
        }

        // Restauramos la pila original
        while (!aux.pilaVacia()) {
            pila.apilar(aux.tope());
            aux.desapilar();
        }


        // Hacemos el cálculo del porcentaje
        return ((cantidadPares / cantidadElementos) * 100);
    }

    /**
     * Ejercicio 7:
     * <p>
     * Se define un método que reciba una PilaTDA y devuelva un ConjuntoTDA
     * con los elementos repetidos de la pila.
     * <p>
     * Complejidad Polinómica
     */
    private ConjuntoTDA conjuntoDeElementosRepetidosDePila(PilaTDA pila) {
        // Creamos un conjunto para almacenar los elementos únicos
        ConjuntoTDA conjunto = new Conjunto();
        conjunto.inicializarConjunto();

        // Creamos una pila auxiliar para restaurar la original
        PilaTDA aux = new Pila();
        aux.inicializarPila();

        // Arreglo para contar ocurrencias
        int[] elementos = new int[100];
        int indiceElementos = 0;

        // Movemos los elementos de la pila a la pila auxiliar
        // y los guardamos en un arreglo para contar las ocurrencias.
        while (!pila.pilaVacia()) {
            int elemento = pila.tope();
            pila.desapilar();
            aux.apilar(elemento);
            elementos[indiceElementos] = elemento;
            indiceElementos++;
        }

        // Recorremos el arreglo con dos bucles anidados para encontrar los elementos repetidos.
        for (int i = 0; i < indiceElementos; i++) {
            for (int j = i + 1; j < indiceElementos; j++) {
                if (elementos[j] == elementos[i]) {
                    conjunto.agregar(elementos[i]);
                }
            }
        }

        // Restauramos la pila original desde la pila auxiliar
        while (!aux.pilaVacia()) {
            pila.apilar(aux.tope());
            aux.desapilar();
        }

        return conjunto;
    }

    /**
     * Ejercicio 8:
     * <p>
     * Se define un método que reciba una ColaTDA y devuelva una nueva ColaTDA con
     * los elementos de la original, sin ninguna repetición. Se debe dejar el primer
     * representante de cada uno de los repetidos, respetando el orden en que aparecen
     * todos los elementos en la original.
     * <p>
     * Complejidad Polinómica
     */
    private ColaTDA removerElementosDuplicados(ColaTDA cola) {
        // Creamos una nueva cola para devolver los elementos
        ColaTDA nuevaCola = new Cola();
        nuevaCola.inicializarCola();

        if (cola.colaVacia()) {
            // Si la cola está vacía, no hay nada que hacer
            return cola;
        }

        // Creamos un arreglo para guardar los elementos.
        int[] elementos = new int[100];
        int indiceElementos = 0;

        // Movemos los elementos de la cola a la cola auxiliar
        // y los guardamos en un arreglo para contar las ocurrencias.
        while (!cola.colaVacia()) {
            int elemento = cola.primero();
            cola.desacolar();

            elementos[indiceElementos] = elemento;
            indiceElementos++;
        }

        // Iteramos por los elementos recibidos.
        for (int i = 0; i < indiceElementos; i++) {

            boolean yaAparecio = false;

            // Iteramos por los elementos anteriores, para ver si ya aparecio antes.
            for (int j = 0; j < i; j++) {
                if (elementos[j] == elementos[i]) {
                    yaAparecio = true;
                }
            }

            // Si el elemento todavia no habia aparecido
            // Lo agregamos a la nueva colar
            if (!yaAparecio) {

                nuevaCola.acolar(elementos[i]);
            }

            // Volvemos a agregar todos los elementos a la cola original.
            cola.acolar(elementos[i]);
        }

        // Retornamos la nueva cola sin duplicados
        return nuevaCola;
    }

    /**
     * Ejercicio 9:
     * <p>
     * Se define un método que reciba una PilaTDA y una ColaTDA y devuelva un
     * ConjuntoTDA con los elementos comunes de la pila y de la cola.
     * <p>
     * Complejidad Polinómica
     */
    private ConjuntoTDA comunesEntreColaYPila(PilaTDA pila, ColaTDA cola) {
        ConjuntoTDA conjunto = new Conjunto();
        conjunto.inicializarConjunto();

        // Creamos una pila auxiliar para restaurar la original.
        PilaTDA pilaAux = new Pila();
        pilaAux.inicializarPila();

        // En la cola usamos un array, porque es mas fácil para buscar
        // Conicidencias, y no necesitamos otra cola para mantener el orden correcto.
        int[] elementosCola = new int[100];
        int indiceCola = 0;

        // Primero guardamos todos los elementos de la pila en otra pila.
        while (!pila.pilaVacia()) {
            pilaAux.apilar(pila.tope());

            pila.desapilar();
        }

        // Guardamos los elementos de la cola en un array.
        while (!cola.colaVacia()) {
            elementosCola[indiceCola] = cola.primero();
            indiceCola++;
            cola.desacolar();
        }

        // Después recorremos cada elemento de la pila auxiliar,
        while (!pilaAux.pilaVacia()) {

            int elementoPila = pilaAux.tope();

            // Y buscamos si es igual a uno de la cola.
            for (int i = 0; i < indiceCola; i++) {
                // Si es igual lo agregamos al conjunto.
                if (elementosCola[i] == elementoPila) {
                    conjunto.agregar(elementoPila);
                }
            }

            // Cuando terminamos con ese elemento,
            // lo devolvemos a la pila original.
            pilaAux.desapilar();
            pila.apilar(elementoPila);
        }

        for (int i = 0; i < indiceCola; i++) {
            // volvemos a agregar el elemento a la cola original.
            cola.acolar(elementosCola[i]);
        }


        return conjunto;
    }

    /**
     * Ejercicio 10:
     * <p>
     * Se define un método que reciba una PilaTDA y devuelva un DiccionarioSimpleTDA,
     * en el cual se guardarán los elementos de la pila como claves, y la
     * cantidad de apariciones de dicho elemento en la pila, como valores.
     * <p>
     * Complejidad Polinómica
     */
    private DiccionarioSimpleTDA diccionarioDeAparicionesEnPila(PilaTDA pila) {
        DiccionarioSimpleTDA diccionario = new imple.DiccionarioSimple();
        diccionario.inicializarDiccionario();

        // Creamos una pila auxiliar para restaurar la original.
        PilaTDA pilaAux = new Pila();
        pilaAux.inicializarPila();

        // Recorremos la pila y contamos las apariciones de cada elemento.

        while (!pila.pilaVacia()) {
            ConjuntoTDA clavesDicc = diccionario.claves();
            int elemento = pila.tope();
            pila.desapilar();
            pilaAux.apilar(elemento);

            if (clavesDicc.pertenece(elemento)) {
                int apariciones = diccionario.recuperar(elemento);
                diccionario.agregar(elemento, apariciones + 1);
            } else {
                diccionario.agregar(elemento, 1);
            }
        }

        // Restauramos la pila original
        while (!pilaAux.pilaVacia()) {
            pila.apilar(pilaAux.tope());
            pilaAux.desapilar();
        }

        return diccionario;
    }

    /**
     * Ejercicio 11:
     * Se define un método que reciba un DiccionarioMultipleTDA y devuelva una ColaTDA
     * con todos los valores del diccionario, sin ninguna repetición.
     * <p>
     * Complejidad Polinómica
     */
    private ColaTDA valoresDiccionarioSinRepetidosACola(DiccionarioMultipleTDA diccionario) {
        // Creamos la cola que vamos a devolver.
        ColaTDA cola = new Cola();
        cola.inicializarCola();

        // Obtenemos las claves del diccionario
        ConjuntoTDA claves = diccionario.claves();

        // Creamos un conjunto para almacenar los valores únicos
        ConjuntoTDA valoresUnicos = new Conjunto();
        valoresUnicos.inicializarConjunto();

        // Recorremos las claves y las agregamos a la cola
        while (!claves.conjuntoVacio()) {
            int clave = claves.elegir();

            ConjuntoTDA valores = diccionario.recuperar(clave);

            while (!valores.conjuntoVacio()) {
                int valor = valores.elegir();

                // Si el valor no está en el conjunto de valores únicos,
                // lo agregamos
                if (!valoresUnicos.pertenece(valor)) {
                    cola.acolar(valor);
                    valoresUnicos.agregar(valor);
                }

                // Sacamos el valor del conjunto para evitar duplicados
                valores.sacar(valor);
            }

        }

        return cola;
    }

    /**
     * Ejercicio 12:
     * <p>
     * Se define un método que calcule la suma de los elementos
     * con un valor impar de un ABB
     * <p>
     * Usamos recursividad para recorrer el árbol.
     */
    private int sumaElementosConValorImparABB(ABBTDA abb) {

        // Si el arbol esta vacio, devolvemos 0;
        if (abb.arbolVacio()) {
            return 0;
        }

        int suma = 0;

        int raiz = abb.raiz();

        // Si la raiz es impar, la sumamos
        if (raiz % 2 != 0) {
            suma += raiz;
        }

        // Sumamos los elementos del hijo izquierdo
        suma += sumaElementosConValorImparABB(abb.hijoIzq());
        // Sumamos los elementos del hijo derecho
        suma += sumaElementosConValorImparABB(abb.hijoDer());

        return suma;
    }

    /**
     * Ejercicio 13:
     * <p>
     * Se define un método que calcule la cantidad de hojas con un valor par de un ABB
     * <p>
     * Recorremos el arbol con recursividad.
     */
    private int cantidadHojasParABB(ABBTDA abb) {
        // Si el arbol esta vacio no hay hojas, devolvemos 0;
        if (abb.arbolVacio()) {
            return 0;
        }

        // Verificamos si el nodo actual es una hoja. Revisando si ambos hijos estan vacios.
        boolean esHoja = abb.hijoIzq().arbolVacio() && abb.hijoDer().arbolVacio();

        int cantidadHojasPar = 0;

        if (esHoja) {
            // Si es una hoja, verificamos si su valor es par
            if (abb.raiz() % 2 == 0) {
                cantidadHojasPar = 1; // Contamos esta hoja
            }
        } else {
            // Si no es una hoja, seguimos buscando en los hijos
            cantidadHojasPar += cantidadHojasParABB(abb.hijoIzq());
            cantidadHojasPar += cantidadHojasParABB(abb.hijoDer());
        }


        return cantidadHojasPar;

    }


    /**
     * Ejercicio 14:
     * <p>
     * Se define un método que reciba un `GrafoTDA` y dos enteros que representen vértices,
     * y devuelva un `ConjuntoTDA` con todos los vértices puente entre los vértices
     * recibidos por parámetro. Se define que un vértice `p` es puente entre dos
     * vértices `o` y `d`, si hay una arista que comienza en `o` y termina en `p` y otra
     * que comienza en `p` y termina en `d`
     * <p>
     * Complejidad Polinómica
     */
    private ConjuntoTDA verticesPuenteEntre(GrafoTDA grafo, int verticeA, int verticeB) {
        // Creamos un conjunto para almacenar los vértices puente
        ConjuntoTDA conjuntoPuente = new Conjunto();
        conjuntoPuente.inicializarConjunto();

        // Obtenemos todos los vértices del grafo
        ConjuntoTDA todosVertices = grafo.vertices();

        // Recorremos cada vértice para verificar si es puente
        while (!todosVertices.conjuntoVacio()) {
            int verticeActual = todosVertices.elegir();
            todosVertices.sacar(verticeActual);

            // Verificamos si hay arista de A → actual y actual → B
            if (grafo.existeArista(verticeA, verticeActual) &&
                    grafo.existeArista(verticeActual, verticeB)) {
                conjuntoPuente.agregar(verticeActual);
            }
        }

        return conjuntoPuente;
    }

    /**
     * Ejercicio 15:
     * <p>
     * Se define un método que reciba un `GrafoTDA` y un entero que represente un vértice,
     * y devuelva el grado del vértice recibido por parámetro. Se define el grado de un
     * vértice `v` como el entero que es igual a la resta entre la cantidad de aristas que
     * salen de `v` menos la cantidad de aristas que llegan a `v`.
     * <p>
     * Complejidad Polinomica
     */
    private int calcularGradoVertice(GrafoTDA grafo, int vertice) {
        int aristasSalientes = 0;
        int aristasEntrantes = 0;

        // Obtenemos todos los vértices del grafo
        ConjuntoTDA todosVertices = grafo.vertices();

        // Recorremos todos los vértices para contar aristas
        while (!todosVertices.conjuntoVacio()) {
            int otroVertice = todosVertices.elegir();
            todosVertices.sacar(otroVertice);

            // Contamos aristas salientes (vertice → otroVertice)
            if (grafo.existeArista(vertice, otroVertice)) {
                aristasSalientes++;
            }

            // Contamos aristas entrantes (otroVertice → vertice)
            if (grafo.existeArista(otroVertice, vertice)) {
                aristasEntrantes++;
            }
        }

        return aristasSalientes - aristasEntrantes;
    }

}



