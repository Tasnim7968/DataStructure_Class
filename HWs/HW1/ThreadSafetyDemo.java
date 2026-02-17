import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ThreadSafetyDemo {

    public static void main(String[] args) throws InterruptedException {
        Vector<Product> sharedVector = new Vector<>();
        List<Product> sharedArrayList = new ArrayList<>();

        int threads = 6;
        int opsPerThread = 2000;

        System.out.println(" THREAD SAFETY ");

        long vTime = runConcurrentTest(sharedVector, threads, opsPerThread, true);
        System.out.println("Vector size after threads: " + sharedVector.size());
        System.out.printf("Vector time: %.3f ms%n", vTime / 1_000_000.0);


        long aTime = runConcurrentTest(sharedArrayList, threads, opsPerThread, false);
        System.out.println("ArrayList size after threads: " + sharedArrayList.size());
        System.out.printf("ArrayList time: %.3f ms%n", aTime / 1_000_000.0);

        System.out.println("\nNotes:");
        System.out.println("- Vector synchronizes methods, so individual add/remove/get are thread-safe.");
        System.out.println("- ArrayList is not thread-safe; concurrent modification can cause issues or inconsistent sizes.");
        System.out.println("- In real apps, prefer Collections.synchronizedList or CopyOnWriteArrayList or external locking.");
    }

    private static long runConcurrentTest(Object list, int threadCount, int ops, boolean isVector) throws InterruptedException {
        Thread[] ts = new Thread[threadCount];
        long start = System.nanoTime();

        for (int t = 0; t < threadCount; t++) {
            final int tid = t;
            ts[t] = new Thread(() -> {
                for (int i = 0; i < ops; i++) {
                    Product p = new Product("T" + tid + "_" + i, "Item", "Cat", 1.0, 1, "Supp");

                    try {
                        if (isVector) {
                            Vector<Product> v = (Vector<Product>) list;
                            v.add(p);
                            if (!v.isEmpty()) v.get(v.size() - 1);
                            if (v.size() > 10) v.remove(0);
                        } else {
                            List<Product> a = (List<Product>) list;
                            a.add(p); // NOT thread-safe
                            if (!a.isEmpty()) a.get(a.size() - 1);
                            if (a.size() > 10) a.remove(0);
                        }
                    } catch (Exception e) {

                    }
                }
            });
        }

        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();

        return System.nanoTime() - start;
    }
}
