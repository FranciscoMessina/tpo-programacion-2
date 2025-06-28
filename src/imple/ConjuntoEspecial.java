package imple;
import tda.ConjuntoEspecialTDA;
/*


Ejercicio 1:

Conjunto Especial

Se define un nuevo TDA denominado ConjuntoEspecialTDA basado en ConjuntoTDA, con la particularidad de permitir determinar si las operaciones se realizan correctamente, o no. Algunos de sus métodos devuelven el objeto Respuesta, que contiene dos elementos: un booleano que determina la correctitud de ejecución y un entero que informa lo solicitado por el metodo en sí, si el metodo lo requiere y su ejecución fue satisfactoria. Su especificación se muestra en el anexo, leer detenidamente los comentarios de cada metodo.
 */
public class ConjuntoEspecial implements ConjuntoEspecialTDA {
    private int[] a;
    private int cant;

    // Complejidad Constante
    public void inicializarConjunto() {
        this.a = new int[100];
        this.cant = 0;
    }

    // Complejidad Lineal
    // Este metodo indica que hubo un error si no se agrega ningún elemento al conjunto
    public Respuesta agregar(int x) {
        Respuesta respuesta = new Respuesta();

        // Empezamos considerando que el metodo va a fallar,
        // Y en caso de que no falle, modificamos el valor
        // mas adelante.
        respuesta.error = true;

        // Si el elemento no pertenece lo agregamos.
        if (!this.pertenece(x)) {
            this.a[this.cant] = x;
            ++this.cant;

            // Como agregamos un elemento, modificamos la respuesta,
            // Indicando que no hay error.
            respuesta.error = false;
        }

        // Devolvemos la respuesta
        return respuesta;
    }

    // Complejidad Constante
    public boolean conjuntoVacio() {
        return this.cant == 0;
    }

    // Complejidad Constante
    // Este metodo indica un error si el conjunto esta vacio
    public Respuesta elegir() {
        Respuesta respuesta = new Respuesta();


        // Si el conjunto esta vacio, modificamos la respuesta
        // para indicar un error, y la devolvemos.
        if(this.conjuntoVacio()) {
            respuesta.error = true;
            return respuesta;
        }

        int max = this.cant - 1;
        int min = 0;
        int pos = (int) (Math.random() * (double) (max - min + 1) + (double) min);

        // Si el conjunto no estaba vacio, se agarro un elemento al azar, y
        // se modifica la respuesta para indicar que no hubo error,
        // y incluir el valor elegido.
        respuesta.error = false;
        respuesta.rta = this.a[pos];

        // Devolvemos la respuesta con el valor elegido del conjunto.
        return respuesta;
    }

    // Complejidad Lineal
    public boolean pertenece(int x) {
        int i;
        for (i = 0; i < this.cant && this.a[i] != x; ++i) {
        }

        return i < this.cant;
    }

    // Complejidad Lineal
    // Para este metodo tenemos que considerar que la Respuesta indica un error en el caso
    // de que no se elimine un elemento.
    public Respuesta sacar(int x) {
        Respuesta respuesta = new Respuesta();

        // Empezamos considerando que el metodo va a fallar,
        // Y en caso de que no falle, modificamos el valor
        // mas adelante.
        respuesta.error = true;

        int i;
        // Este for frena cuando el elemento es igual al buscado para sacar,
        for (i = 0; i < this.cant && this.a[i] != x; ++i) {
        }


        // Si i es menor a la cantidad de elementos quiere decir que encontramos
        // el elemento buscado.
        if (i < this.cant) {
            this.a[i] = this.a[this.cant - 1];
            --this.cant;

            // Como el elemento fue eliminado, establecemos que no hubo error en la operacion.
            respuesta.error = false;
        }


        // Devolvemos la respuesta con los datos configurados
        // dependiendo del resultado de la ejecucion del metodo.
        return respuesta;
    }
}