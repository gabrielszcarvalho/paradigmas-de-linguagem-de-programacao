// Prática 1 - Condição de corrida (Sebesta 13.2.1, p. 547-549)
// Cole em https://onecompiler.com/java
public class Main {
    static int contador = 0;                 // variável COMPARTILHADA

    public static void main(String[] args) throws InterruptedException {
        int n = 1_000_000;                   // repetições de cada thread
        Runnable incrementa = () -> {
            for (int i = 0; i < n; i++) contador++;
        };
        Thread t1 = new Thread(incrementa);
        Thread t2 = new Thread(incrementa);
        t1.start(); t2.start();              // as duas executam ao mesmo tempo
        t1.join();  t2.join();               // espera as duas terminarem
        System.out.println("esperado: " + 2 * n);
        System.out.println("obtido:   " + contador);
    }
}
