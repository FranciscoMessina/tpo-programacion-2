package imple;

import tda.MultiPilaTDA;
import tda.PilaTDA;

/*
Ejercicio 3:

Se define un nuevo TDA denominado MultiPilaTDA basado en PilaTDA, con la particularidad de recibir una PilaTDA por parámetro al apilar
(la misma debe apilarse a continuación de la multipila), y otra al desapilar (la misma debe chequear que los valores tope de la multipila
coincidan para desapilar, sino no debe hacer nada). Tanto en el método apilar como en el método desapilar, ambas pilas vienen inicializadas
y contienen cualquier cantidad de elementos (incluso cero). El método tope devuelve una PilaTDA con los primeros elementos de la multipila,
se recibe por parámetro un número mayor o igual que cero, que representa la cantidad de ellos (de recibir un número superior a la
cantidad de elementos de la multipila, debe devolver todos). Se solicita realizar la presente implementación con el TDA ya visto PilaTDA,
o en su defecto con estructuras dinámicas (no puede realizarse la implementación con estructuras estáticas). Su especificación se muestra en el anexo,
leer detenidamente los comentarios de cada método.
 */
public class MultiPila implements MultiPilaTDA {

    private class Nodo {
        int info;
        Nodo sig;
    }


    private Nodo primero;

    // Complejidad Constante
    @Override
    public void inicializarPila() {
        this.primero = null;

    }

    // Complejidad Constante
    @Override
    public boolean pilaVacia() {
        return this.primero == null;
    }

    /**
     * Inserta la pila recibida en el tope de la multipila.
     * Si la multipila actualmente es: (tope) 3 - 5 - 7
     * Y la pila que se recibe es: (tope) 1 - 9
     * La multipila debe quedar: (tope) 1 - 9 - 3 - 5 - 7
     */
    @Override
    // Complejidad Lineal
    public void apilar(PilaTDA valores) {

        if (valores.pilaVacia()) {
            // No hacemos nada si la pila recibida está vacía
            return;
        }

        // Creamos una pila auxiliar .
        PilaTDA aux = new Pila();
        aux.inicializarPila();

        // Copiamos los valores de la pila recibida a una pila auxiliar,
        while (!valores.pilaVacia()) {
            aux.apilar(valores.tope());
            valores.desapilar();
        }

        // Apilar los elementos de la pila de valores en la multipila
        // y restaurar los valores a la pila de valores.
        // Los apilamos en la MultiPila en este While, para que queden en el orden correcto.
        // Si lo hicieramos en el anterior quedarian al reves.
        while (!aux.pilaVacia()) {
            int topAux = aux.tope();
            valores.apilar(topAux);
            aux.desapilar();
            Nodo nuevo = new Nodo();
            nuevo.info = topAux;
            nuevo.sig = this.primero; // El nuevo nodo apunta al primer nodo de la multipila
            this.primero = nuevo;

        }
    }

    /**
     * Desapila la pila recibida por parámetro de la multipila,
     * solo si el tope de la multipila coincide con la pila recibida.
     * Si la multipila actualmente es: (tope) 7 - 2 - 8-9
     * Y la pila que se recibe es: (tope) 7 - 2
     * La multipila debe quedar: (tope) 8 - 9
     * Si en cambio la pila que se recibe es: (tope) 7 - 2 - 3
     * No deben realizarse cambios en la multipila,
     * dado que no coincide con la pila recibida.
     */
    @Override
    // Complejidad Lineal
    public void desapilar(PilaTDA valores) {

        if (valores.pilaVacia()) {
            // Si la pila recibida está vacía, no hacemos nada.
            return;
        }
        // Creamos una pila auxiliar para poder restaurar los valores
        // de la pila recibida al final del proceso.
        PilaTDA aux = new Pila();
        aux.inicializarPila();

        // Pila para guardar los valores recibidos usando estructuras dinámicas
        PilaTDA valoresRecibidos = new Pila();
        valoresRecibidos.inicializarPila();

        int cantidadDeValoresRecibidos = 0;


        // Por cada elemento de la pila recibida, sumamos uno al contador.
        // Y guardamos el valor en una pila para poder comparar luego.
        while (!valores.pilaVacia()) {
            int topeValor = valores.tope();
            aux.apilar(topeValor);
            valoresRecibidos.apilar(topeValor);
            valores.desapilar();

            // Incrementamos el contador de valores.
            cantidadDeValoresRecibidos++;
        }

        // Pedimos el tope de la multipila para comparar con los valores recibidos.
        PilaTDA topeMultipila = this.tope(cantidadDeValoresRecibidos);

        boolean topesIguales = true;

        PilaTDA auxComparacion = new Pila();
        auxComparacion.inicializarPila();

        // Copiamos valoresRecibidos para no modificarla
        while (!valoresRecibidos.pilaVacia()) {
            auxComparacion.apilar(valoresRecibidos.tope());
            valoresRecibidos.desapilar();
        }

        // Verificamos que los valores recibidos coincidan con los topes de la multipila.
        while(!auxComparacion.pilaVacia() && !topeMultipila.pilaVacia()) {
            if(auxComparacion.tope() != topeMultipila.tope()) {
                topesIguales = false;
            }
            auxComparacion.desapilar();
            topeMultipila.desapilar();
        }

        if (!topesIguales) {
            // Si los topes no son iguales, restauramos los valores de la pila recibida.
            // Y no hacemos nada con la multipila.
            while (!aux.pilaVacia()) {
                valores.apilar(aux.tope());
                aux.desapilar();
            }
            return;
        }

        // Restamos al índice la cantidad de valores que recibimos en la pila de valores.

        for (int i = 0; i < cantidadDeValoresRecibidos; i++) {
            this.primero = this.primero.sig;
        }
        // Restauramos los valores de la pila recibida.
        while (!aux.pilaVacia()) {
            valores.apilar(aux.tope());
            aux.desapilar();
        }

    }

    /**
     * Devuelve una pila con los valores que estén en el tope de la multipila.
     * La cantidad de valores a devolver se define por parámetro y debe
     * preservarse el orden.
     * Si la cantidad es mayor al tamaño actual de la multipila,
     * se devuelven todos los valores de la multipila.
     * Si la multipila actualmente es: (tope) 4 - 2 - 9 - 7
     * Y se recibe por parámetro un 2, debe devolverse la pila: (tope) 4 - 2
     * Si se recibe por parámetro un 5, debe devolverse la pila: (tope) 4 - 2 - 9 - 7
     */
    @Override
    // Complejidad Lineal
    public PilaTDA tope(int cantidad) {
        // Creamos una pila, en la cual vamos a almacenar los valores a devolver.
        PilaTDA pila = new Pila();
        pila.inicializarPila();

        // Creamos otra pila para poder devolver los valores en el orden correcto.
        PilaTDA pilaAux = new Pila();
        pilaAux.inicializarPila();

        Nodo actual = this.primero;
        int contador = 0;

        // Iteramos hasta que un elemento sea nulo o hayamos alcanzado la cantidad solicitada.
        while(actual != null && contador < cantidad) {
            // Contamos los elementos de la multipila hasta la cantidad solicitada.
            pilaAux.apilar(actual.info);
            actual = actual.sig;
            contador++;
        }


        while (!pilaAux.pilaVacia()) {
            // Desapilamos los elementos de la pila auxiliar y los apilamos en la pila a devolver.
            pila.apilar(pilaAux.tope());
            pilaAux.desapilar();
        }

        return pila;
    }


}
