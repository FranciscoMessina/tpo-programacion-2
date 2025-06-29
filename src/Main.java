import imple.ConjuntoMamushka;
import imple.MultiPila;
import imple.Pila;
import tda.ConjuntoMamushkaTDA;
import tda.MultiPilaTDA;
import tda.PilaTDA;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        pruebasMultiPila();
        pruebasConjuntoMamushka();


    }

    private static void pruebasConjuntoMamushka() {
        ConjuntoMamushkaTDA conjunto = new ConjuntoMamushka();

        conjunto.inicializar();
        conjunto.guardar(2);
        conjunto.guardar(2);
        conjunto.guardar(1);
        conjunto.guardar(3);

        System.out.println("Elemento elegido: " + conjunto.elegir());

        System.out.println("El 1 esta " + conjunto.perteneceCant(1) + " veces en el conjunto.");

        conjunto.sacar(2);
        System.out.println("Elemento elegido después de sacar 2: " + conjunto.elegir());
        conjunto.sacar(2);
        System.out.println("Elemento elegido después de sacar 2 nuevamente: " + conjunto.elegir());
    }

    private static void pruebasMultiPila() {
        MultiPilaTDA multiPila = new MultiPila();

        multiPila.inicializarPila();
        PilaTDA pila1 = new Pila();
        pila1.inicializarPila();
        pila1.apilar(1);
        pila1.apilar(2);
        pila1.apilar(3);
        PilaTDA pila2 = new Pila();
        pila2.inicializarPila();
        pila2.apilar(4);
        pila2.apilar(5);
        pila2.apilar(6);

        multiPila.apilar(pila1);
        System.out.println("Pila1 Apilada");
        multiPila.apilar(pila2);
        System.out.println("Pila2 Apilada");

        PilaTDA topeMultiPila = multiPila.tope(2);
        System.out.println("Tope de la MultiPila con 2 elementos:");

        multiPila.desapilar(pila1);
        System.out.println("Pila2 Desapilada");

        PilaTDA topeMultiPila2 = multiPila.tope(3);
        System.out.println("Nuevo tope de la multiPila.");

        PilaTDA topeMultiPila3 = multiPila.tope(10);
        System.out.println("Tope de la MultiPila con 10 elementos (debería devolver todos los elementos):");
    }
}