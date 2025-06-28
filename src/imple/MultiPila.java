package imple;

import tda.MultiPilaTDA;
import tda.PilaTDA;

/*
Ejercicio 3

Se define un nuevo TDA denominado MultiPilaTDA basado en PilaTDA, con la particularidad de recibir una PilaTDA por parámetro al apilar (la misma debe apilarse a continuación de la multipila), y otra al desapilar (la misma debe chequear que los valores tope de la multipila coincidan para desapilar, sino no debe hacer nada). Tanto en el método apilar como en el método desapilar, ambas pilas vienen inicializadas y contienen cualquier cantidad de elementos (incluso cero). El método tope devuelve una PilaTDA con los primeros elementos de la multipila, se recibe por parámetro un número mayor o igual que cero, que representa la cantidad de ellos (de recibir un número superior a la cantidad de elementos de la multipila, debe devolver todos). Se solicita realizar la presente implementación con el TDA ya visto PilaTDA, o en su defecto con estructuras dinámicas (no puede realizarse la implementación con estructuras estáticas). Su especificación se muestra en el anexo, leer detenidamente los comentarios de cada método.
 */
public class MultiPila implements MultiPilaTDA {
    private int[] elementos;
    private int indice;

    // Complejidad Constante
    @Override
    public void inicializarPila() {
        this.elementos = new int[100];
        this.indice = 0;
    }

    // Complejidad Constante
    @Override
    public boolean pilaVacia() {
        return this.indice == 0;
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
            this.elementos[this.indice] = topAux;
            this.indice++;
            aux.desapilar();
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
        // Creamos una pila auxiliar para poder restaurar los valores
        // de la pila recibida al final del proceso.
        PilaTDA aux = new Pila();
        aux.inicializarPila();

        // Creamos una variable para contar la cantidad de valores que tiene la pila recibiad.
        int cantidadDeValoresRecibidos = 0;
        int[] valoresRecibidos = new int[100];


        // Por cada elemento de la pila recibida, sumamos uno al contador.
        // Y guardamos el valor en un array para poder comparar luego.
        while (!valores.pilaVacia()) {
            int topeValor = valores.tope();
            aux.apilar(topeValor);
            valores.desapilar();
            // Guardamos el valor en el array de valores recibidos.
            valoresRecibidos[cantidadDeValoresRecibidos] = topeValor;
            // Incrementamos el contador de valores.
            cantidadDeValoresRecibidos++;
        }

        // Pedimos el tope de la multipila para comparar con los valores recibidos.
        PilaTDA topeMultipila = this.tope(cantidadDeValoresRecibidos);

        boolean topesIguales = true;
        for (int i = 0; i < cantidadDeValoresRecibidos; i++) {
            if (valoresRecibidos[i] != topeMultipila.tope()) {
                topesIguales = false;
            }
            topeMultipila.desapilar();
        }

        if (!topesIguales) {
            // Si los topes no son iguales, restauramos los valores de la pila recibida.
            while (!aux.pilaVacia()) {
                valores.apilar(aux.tope());
                aux.desapilar();
            }
            return; // No hacemos nada más si los topes no coinciden.
        }


        for (int i = 0; i < cantidadDeValoresRecibidos; i++) {
            System.out.print(valoresRecibidos[i] + " ");
        }

        // Reducimso el indice en la cantidad de valores que recibimos en la pila de valores.
        // Asi desapilando esos elementos de la multipila.
        this.indice = this.indice - cantidadDeValoresRecibidos;
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

        System.out.println("Cantidad solicitada: " + cantidad);
        System.out.println("Indice actual: " + this.indice);
        // Iteramos desde el índice actual hacia atras, hasta llegar a la cantidad de elementos solicitados para devolver.
        for (int i = this.indice - 1; i > this.indice - (cantidad + 1) && i >= 0; i--) {
            System.out.println("i en TOPE" + i);

            // Agregamos el elemento a la pila a devolver.
            pilaAux.apilar(this.elementos[i]);
        }

        while (!pilaAux.pilaVacia()) {
            // Desapilamos los elementos de la pila auxiliar y los apilamos en la pila a devolver.
            int topeAux = pilaAux.tope();
            pila.apilar(topeAux);
            pilaAux.desapilar();
        }

        return pila;
    }


}
