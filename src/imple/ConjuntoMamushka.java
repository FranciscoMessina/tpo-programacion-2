package imple;

import tda.ConjuntoMamushkaTDA;

/**
 * Ejercicio 2:
 * <p>
 * Se define un nuevo TDA denominado ConjuntoMamushkaTDA basado en ConjuntoTDA, con la particularidad de que se permite más de una acepción de cada elemento agregado.
 * Tal cual como en ConjuntoTDA, no existe orden alguno. Su especificación se muestra en el anexo, leer detenidamente los comentarios de cada método.
 */
public class ConjuntoMamushka implements ConjuntoMamushkaTDA {

    private int[] a;
    private int cant;

    // Complejidad Constante
    @Override
    public void inicializar() {
        this.a = new int[100];
        this.cant = 0;
    }


    @Override
    // Complejidad Constante
    // Como este conjunto permite datos repetidos,
    // Agregamos siempre el nuevo dato al final, sin importar si ya existe
    public void guardar(int dato) {
        this.a[this.cant] = dato;
        ++this.cant;

    }

    // Complejidad Lineal
    // Saca la primera ocurrencia encontrada del argumento recibido
    @Override
    public void sacar(int dato) {
        int i;
        // Este for frena cuando el elemento es igual al buscado para sacar,
        for (i = 0; i < this.cant && this.a[i] != dato; ++i) {
        }


        // Si i es menor a la cantidad de elementos quiere decir que encontramos
        // el elemento buscado.
        if (i < this.cant) {
            this.a[i] = this.a[this.cant - 1];
            --this.cant;
        }
    }

    // Complejidad Constante
    @Override
    public int elegir() {

        int max = this.cant - 1;
        int min = 0;
        int pos = (int) (Math.random() * (double) (max - min + 1) + (double) min);


        return this.a[pos];
    }


    @Override
    // Complejidad Lineal
    // Cuenta la cantidad de veces que un elemento aparece en el conjunto,
    // Y devuelve ese número.
    public int perteneceCant(int dato) {
        // Creamos una variable para contar las ocurrencias del dato
        int cantidadDeOcurrencias = 0;

        // Recorremos los valores del conjunto
        // Cuando uno de ellos es igual al dato que buscamos,
        // Aumentamos el contador.
        for (int i = 0; i < this.cant; i++) {
            if (this.a[i] == dato) {
                cantidadDeOcurrencias++;
            }
        }
        return cantidadDeOcurrencias;
    }

    // Complejidad Constante
    @Override
    public boolean estaVacio() {
        return this.cant == 0;
    }
}
