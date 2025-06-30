package imple;

import tda.ColaPrioridadTDA;
import tda.ConjuntoTDA;
import tda.DiccionarioSimpleTDA;

/*
Ejercicio 5:

Se busca implementar un DiccionarioSimpleTDA usando únicamente una ColaPrioridadTDA.
Aclaración: se mantiene la interfaz de DiccionarioSimpleTDA; en la implementación
en vez de utilizar un arreglo de enteros (estructura estática) o una
lista enlazada (estructura dinámica), sólo puede usarse una ColaPrioridadTDA.
 */
public class DiccionarioSimpleConColaPrioridad implements DiccionarioSimpleTDA {
    private ColaPrioridadTDA cola;

    @Override
    public void inicializarDiccionario() {
        this.cola = new ColaPrioridad();
        this.cola.inicializarCola();
    }

    @Override
    // Complejidad Polinomica
    public void agregar(int i, int i1) {
        ColaPrioridadTDA colaAux = new ColaPrioridad();
        colaAux.inicializarCola();

        boolean claveExistente = false;
        // Recorremos la cola y buscamos si la clave ya existe.
        // Si la clave ya existe, se actualiza su valor.
        // Si la clave no existe, se acola con su valor inicial.
        // Se guardan los valores en una cola auxiliar, que después se usa para llenar la original.
        while(!this.cola.colaVacia()) {
            int clave = this.cola.primero();


            if (clave != i) {
                // Si la clave del elemento actual es diferente a la clave que se quiere agregar,
                // la volvemos a guardar con su valor existente.
                colaAux.acolarPrioridad(clave, this.cola.prioridad());
            } else {
                // Si la clave del elemento actual es igual a la que se quiere agregar, quiere decir que el elemento ya existe.
                // Por lo tanto, actualizamos su valor con el nuevo.
                colaAux.acolarPrioridad(i,i1);
                claveExistente = true;
            }
            this.cola.desacolar();
        }

        // Si la clave no existia, la acola con su valor inicial.
        // Entraria en este caso si es la primera vez que se agrega la clave.
        if(!claveExistente) {
            colaAux.acolarPrioridad(i, i1);
        }

        // Pasamos los valores de la cola auxiliar a la cola original.
        // Ya con el nuevo valor agregado, o actualizado.
       while(!colaAux.colaVacia()) {
            int clave = colaAux.primero();
            int prioridad = colaAux.prioridad();
            this.cola.acolarPrioridad(clave, prioridad);
            colaAux.desacolar();
        }
    }

    @Override
    // Complejidad Polinomica
    public void eliminar(int i) {
        ColaPrioridadTDA colaAux = new ColaPrioridad();
        colaAux.inicializarCola();

        // Recorremos la cola y buscamos la clave a eliminar.
        // Ademas pasamos sus valores a una cola auxiliar para poder restaurar la cola original.
        while (!this.cola.colaVacia()) {
            int clave = this.cola.primero();

            // Si la clave es diferente a la que se quiere eliminar, se acola en la cola auxiliar.
            // Eliminandola la clave deseada del diccionario.
            if (clave != i) {
                colaAux.acolarPrioridad(clave, this.cola.prioridad());
            }
            this.cola.desacolar();
        }

        // Restauramos la cola original desde la cola auxiliar, sin la clave eliminada.
        while (!colaAux.colaVacia()) {
            int clave = colaAux.primero();
            int prioridad = colaAux.prioridad();
            this.cola.acolarPrioridad(clave, prioridad);
            colaAux.desacolar();
        }
    }

    @Override
    public int recuperar(int i) {
        ColaPrioridadTDA colaAux = new ColaPrioridad();
        colaAux.inicializarCola();

        int valor = -1; // Valor por defecto en caso de no encontrar la clave.

        // Recorremos la cola y buscamos la clave.
        // tambien pasamos sus valores a una cola auxiliar para poder restaurar la cola original.
        while (!this.cola.colaVacia()) {
            int clave = this.cola.primero();
            int prioridad = this.cola.prioridad();

            // Si la clave es igual a la que se quiere recuperar
            // guardamos su valor para devolverlo.
            if (clave == i) {
                valor = prioridad;
            }

            colaAux.acolarPrioridad(clave, prioridad);
            this.cola.desacolar();
        }

        // Restauramos la cola original desde la cola auxiliar.
        while (!colaAux.colaVacia()) {
            int clave = colaAux.primero();
            int prioridad = colaAux.prioridad();
            this.cola.acolarPrioridad(clave, prioridad);
            colaAux.desacolar();
        }

        return valor;

    }

    @Override
    public ConjuntoTDA claves() {
        ConjuntoTDA conjunto = new Conjunto();
        conjunto.inicializarConjunto();
        ColaPrioridadTDA colaAux = new ColaPrioridad();
        colaAux.inicializarCola();

        // Recorremos la cola y vamos agregando las claves al conjunto.
        // Ademas pasamos sus valores a una cola auxiliar para poder restaurar la cola original.
        while(!this.cola.colaVacia()) {
            int clave = this.cola.primero();
            int prioridad = this.cola.prioridad();

            // Acolamos la clave en el conjunto.
            conjunto.agregar(clave);
            colaAux.acolarPrioridad(clave, prioridad);
            this.cola.desacolar();
        }

        // Restauramos la cola original desde la cola auxiliar.
        while (!colaAux.colaVacia()) {
            int clave = colaAux.primero();
            int prioridad = colaAux.prioridad();
            this.cola.acolarPrioridad(clave, prioridad);
            colaAux.desacolar();
        }

        return conjunto;

    }
}
