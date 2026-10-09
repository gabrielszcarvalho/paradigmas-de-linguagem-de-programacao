// Prática 4 - Impasse (deadlock) (Sebesta 13.2.1, p. 551)
// Cole em https://onecompiler.com/java
import java.lang.management.ManagementFactory;

public class Main {
    static final Object X = new Object(), Y = new Object();   // dois recursos

    static void pausa() {
        try { Thread.sleep(100); } catch (InterruptedException e) { }
    }

    public static void main(String[] args) throws Exception {
        Thread a = new Thread(() -> {
            synchronized (X) { pausa(); synchronized (Y) { System.out.println("A terminou"); } }
        });
        Thread b = new Thread(() -> {
            synchronized (Y) { pausa(); synchronized (X) { System.out.println("B terminou"); } }
        });
        a.start(); b.start();
        Thread.sleep(1000);                                    // espera 1 segundo
        long[] presas = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        System.out.println(presas == null ? "sem impasse"
                                          : "IMPASSE: " + presas.length + " threads presas");
        System.exit(0);                                        // encerra o programa
    }
}
