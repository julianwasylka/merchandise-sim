import java.util.concurrent.ThreadLocalRandom;

public class Klient implements Runnable {

    private final Magazyn magazyn;

    public Klient(Magazyn magazyn) {
        this.magazyn = magazyn;
    }

    @Override
    public void run() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        try {
            Thread.sleep(random.nextInt(1000, 3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(magazyn.isFlaga()) {
            int index = random.nextInt(magazyn.getTowary().size());
            String key = (String)magazyn.getTowary().keySet().toArray()[index];
            int ilosc = random.nextInt(1, magazyn.getCapacity());
            int time = random.nextInt(5000, 10000);
            try {
                int num = magazyn.decTowar(key, ilosc);
                if (num == ilosc) {
                    System.out.println("Klient " + Thread.currentThread().getName() + " kupil " + ilosc + " " + key);
                }
                else {
                    System.out.println("Klient " + Thread.currentThread().getName() + " chcial kupic " + ilosc + " " + key + ", ale mogl kupic " + num);
                }
                magazyn.printState();
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
