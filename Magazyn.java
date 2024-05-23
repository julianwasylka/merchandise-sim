import java.util.LinkedHashMap;
import java.util.Map;

public class Magazyn {

    private final int Capacity = 10;
    private static Map<String, Integer> towary;
    private volatile boolean flaga;

    public Magazyn() {
        this.towary = new LinkedHashMap<>();
        towary.put("Monitory", 0);
        towary.put("Mychy", 0);
        towary.put("Grafy", 0);
        towary.put("Procki", 0);
        towary.put("Klawy", 0);
        flaga = true;
    }

    public int getCapacity() {
        return Capacity;
    }

    public boolean isFlaga() {
        return flaga;
    }

    public void setFlaga(boolean flaga) {
        this.flaga = flaga;
    }

    public synchronized static Map<String, Integer> getTowary() {
        return towary;
    }

    public synchronized int incTowar(String key, int num) {
        int qty = towary.get(key);
        int newQty;

        if ((qty + num) >= Capacity) {
            newQty = Capacity;
            num = Capacity - qty;
        }
        else newQty = qty + num;
        towary.put(key, newQty);

        return num;
    }

    public synchronized int decTowar(String key, int num) {
        int qty = towary.get(key);
        int newQty;

        if ((qty - num) <= 0) {
            newQty = 0;
            num = qty;
        }
        else newQty = qty - num;
        towary.put(key, newQty);

        return num;
    }

    public synchronized void printState() {
        for (Map.Entry<String, Integer> item : towary.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
    }
}
