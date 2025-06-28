package imple;

import tda.ConjuntoTDA;
import tda.DiccionarioSimpleModTDA;
/*
Ejercicio 4:

Se define un nuevo TDA denominado DiccionarioSimpleModTDA basado en DiccionarioSimpleTDA,
con la particularidad de registrar la cantidad de veces que el valor se ve modificado
(entrada: clave-valor-factorMod). Su especificación se muestra en el anexo,
leer detenidamente los comentarios de cada método.
 */
public class DiccionarioSimpleMod implements DiccionarioSimpleModTDA {
    private class Elemento {
        int clave;
        int valor;
        int vecesModificado;
    }
    Elemento[] elementos;
    int cantidadElementos;

    @Override
    public void inicializarDiccionario() {
        this.elementos = new Elemento[100];
        this.cantidadElementos = 0;
    }

    @Override
    public void agregar(int clave, int valor) {
        // Buscamos a ver si ya existe la clave.
        int indice = this.clave2Indice(clave);
        if(indice < 0) {
            // No existe la clave, se agrega un nuevo elemento
            Elemento nuevoElemento = new Elemento();
            nuevoElemento.clave = clave;
            nuevoElemento.valor = valor;
            nuevoElemento.vecesModificado = 0; // Inicialmente no ha sido modificado
            this.elementos[this.cantidadElementos] = nuevoElemento;
            this.cantidadElementos++;
        } else {
            // La clave ya existe, se actualiza el valor y el contador de modificaciones
            this.elementos[indice].valor = valor;
            this.elementos[indice].vecesModificado++;
        }

    }

    private int clave2Indice(int clave) {
        int i;
        for(i = this.cantidadElementos - 1; i >= 0 && this.elementos[i].clave != clave; --i) {
        }

        return i;
    }

    @Override
    public void eliminar(int clave) {
        int pos = this.clave2Indice(clave);
        if (pos != -1) {
            this.elementos[pos] = this.elementos[this.cantidadElementos - 1];
            --this.cantidadElementos;
        }
    }

    @Override
    public int recuperar(int clave) {
        int pos = this.clave2Indice(clave);
        return this.elementos[pos].valor;
    }

    @Override
    public int recuperarMod(int clave) {
        int pos = this.clave2Indice(clave);
        return this.elementos[pos].vecesModificado;
    }

    @Override
    public ConjuntoTDA claves() {
        ConjuntoTDA c = new Conjunto();
        c.inicializarConjunto();

        for(int i = 0; i < this.cantidadElementos; ++i) {
            c.agregar(this.elementos[i].clave);
        }

        return c;
    }
}
