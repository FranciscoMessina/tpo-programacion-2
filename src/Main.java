import imple.MultiPila;
import imple.Pila;
import tda.MultiPilaTDA;
import tda.PilaTDA;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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

        multiPila.desapilar(pila2);
        System.out.println("Pila2 Desapilada");

        PilaTDA topeMultiPila2 = multiPila.tope(3);
        System.out.println("Nuevo tope de la multiPila.");

        PilaTDA topeMultiPila3 = multiPila.tope(10);
        System.out.println("Tope de la MultiPila con 10 elementos (debería devolver todos los elementos):");
    }
}