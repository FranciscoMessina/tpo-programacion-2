package uso;

import imple.Cola;
import imple.Conjunto;
import imple.Pila;
import tda.ColaTDA;
import tda.ConjuntoTDA;
import tda.PilaTDA;

public class Funciones {
    public static void main(String[] args) {
        Funciones funciones = new Funciones();



    }
    /*
    Ejercicio 6:

    Se define un método que reciba una PilaTDA y devuelva un float (número real)
    con el porcentaje de cantidad de elementos pares de la pila.
     */
    // Complejidad Lineal
    private float porcentajeDeElementosParesEnPila(PilaTDA pila) {
        if(pila.pilaVacia()) {
            return 0.0f; // Si la pila está vacía, el porcentaje es 0
        }

        PilaTDA aux = new Pila();
        aux.inicializarPila();
        float cantidadPares = 0;
        float cantidadElementos = 0;
        // Recorremos la pila y contamos los elementos pares
        // y la cantidad total de elementos.
        // Almacenandolos en una pila auxiliar para poder restaurar la original.
        while(!pila.pilaVacia()) {
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

    /*
    Ejercicio 7:
    Se define un método que reciba una PilaTDA y devuelva un ConjuntoTDA
    con los elementos repetidos de la pila.
     */
    // Complejidad Polinómica
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

    /*
    Ejercicio 8:

    Se define un método que reciba una ColaTDA y devuelva una nueva ColaTDA con
    los elementos de la original, sin ninguna repetición. Se debe dejar el primer
    representante de cada uno de los repetidos, respetando el orden en que aparecen
    todos los elementos en la original.
     */
    // Complejidad Polinómica
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
            if (!yaAparecio){

                nuevaCola.acolar(elementos[i]);
            }

            // Volvemos a agregar todos los elementos a la cola original.
            cola.acolar(elementos[i]);
        }

        // Retornamos la nueva cola sin duplicados
        return nuevaCola;
    }

    /*
    Ejercicio 9:
    Se define un método que reciba una PilaTDA y una ColaTDA y devuelva un
    ConjuntoTDA con los elementos comunes de la pila y de la cola.
     */
    private ConjuntoTDA comunesEntreColaYPila(PilaTDA pila, ColaTDA cola) {
        ConjuntoTDA conjunto = new Conjunto();
        conjunto.inicializarConjunto();

        PilaTDA pilaAux = new Pila();
        pilaAux.inicializarPila();
        ColaTDA colaAux = new Cola();
        colaAux.inicializarCola();

        int[] elementosPila = new int[100];
        int indicePila = 0;
        int[] elementosCola = new int[100];
        int indiceCola = 0;

        while(!pila.pilaVacia()) {
            elementosPila[indicePila] = pila.tope();
            indicePila++;
            pila.desapilar();
        }





        return conjunto;
    }
}
