import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    final static int Knum = 4;
    final static int Pnum = 3;

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService producenci = Executors.newFixedThreadPool(Pnum);
        final ExecutorService klienci = Executors.newFixedThreadPool(Knum);
        final Magazyn magazyn = new Magazyn();

        for (int i = 0; i < Pnum; i++) {
            producenci.submit(new Producent(magazyn));
        }
        for (int i = 0; i < Knum; i++) {
            klienci.submit(new Klient(magazyn));
        }
        producenci.shutdown();
        klienci.shutdown();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
        magazyn.setFlaga(false);
        producenci.awaitTermination(10, TimeUnit.SECONDS);
        klienci.awaitTermination(10, TimeUnit.SECONDS);
    }
}