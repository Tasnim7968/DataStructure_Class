import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class VectorComparisonDemo {

    public static void main(String[] args) {
        Vector<Product> v = new Vector<>();
        ArrayList<Product> a = new ArrayList<>();

        // Same initial products
        Product p1 = new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp");
        Product p2 = new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc");
        Product p3 = new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp");

        v.add(p1); v.add(p2); v.add(p3);
        a.add(p1); a.add(p2); a.add(p3);

        // Measure add 10,000
        int n = 10_000;
        long vAdd = timeAddVector(n);
        long aAdd = timeAddArrayList(n);

        // Measure access 1,000 random
        long vAccess = timeRandomAccessVector(n);
        long aAccess = timeRandomAccessArrayList(n);

        // Memory usage (approx): allocate each list, force GC, then measure delta
        long vMem = estimateVectorMemory(n);
        long aMem = estimateArrayListMemory(n);

        System.out.println("=== Vector vs ArrayList Comparison ===");
        System.out.println("Add 10,000 items:");
        System.out.printf("Vector:    %.3f ms%n", vAdd / 1_000_000.0);
        System.out.printf("ArrayList: %.3f ms%n", aAdd / 1_000_000.0);

        System.out.println("\nAccess 1,000 random elements:");
        System.out.printf("Vector:    %.3f ms%n", vAccess / 1_000_000.0);
        System.out.printf("ArrayList: %.3f ms%n", aAccess / 1_000_000.0);

        System.out.println("\nApprox memory usage after adding 10,000 Products:");
        System.out.printf("Vector:    ~%,d bytes%n", vMem);
        System.out.printf("ArrayList: ~%,d bytes%n", aMem);

        System.out.println("\n=== Summary ===");
        System.out.println("- Vector is synchronized (thread-safe for individual operations), so it may be a bit slower.");
        System.out.println("- ArrayList is not synchronized and is usually faster in single-threaded programs.");
        System.out.println("- Choose Vector when you need legacy compatibility or simple built-in synchronization.");
        System.out.println("- Choose ArrayList for most modern single-threaded or externally-synchronized designs.");
    }

    private static long timeAddVector(int n) {
        Vector<Product> v = new Vector<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            v.add(new Product("VP" + i, "Item" + i, "Cat", 1.0, 1, "Supp"));
        }
        return System.nanoTime() - start;
    }

    private static long timeAddArrayList(int n) {
        ArrayList<Product> a = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            a.add(new Product("AP" + i, "Item" + i, "Cat", 1.0, 1, "Supp"));
        }
        return System.nanoTime() - start;
    }

    private static long timeRandomAccessVector(int n) {
        Vector<Product> v = new Vector<>();
        for (int i = 0; i < n; i++) v.add(new Product("VP" + i, "Item" + i, "Cat", 1.0, 1, "Supp"));

        Random r = new Random(42);
        long start = System.nanoTime();
        double sink = 0;
        for (int i = 0; i < 1000; i++) {
            int idx = r.nextInt(n);
            sink += v.get(idx).getPrice();
        }
        if (sink == -1) System.out.println("ignore " + sink);
        return System.nanoTime() - start;
    }

    private static long timeRandomAccessArrayList(int n) {
        ArrayList<Product> a = new ArrayList<>();
        for (int i = 0; i < n; i++) a.add(new Product("AP" + i, "Item" + i, "Cat", 1.0, 1, "Supp"));

        Random r = new Random(42);
        long start = System.nanoTime();
        double sink = 0;
        for (int i = 0; i < 1000; i++) {
            int idx = r.nextInt(n);
            sink += a.get(idx).getPrice();
        }
        if (sink == -1) System.out.println("ignore " + sink);
        return System.nanoTime() - start;
    }

    private static long estimateVectorMemory(int n) {
        Runtime rt = Runtime.getRuntime();
        System.gc();
        long before = rt.totalMemory() - rt.freeMemory();

        Vector<Product> v = new Vector<>();
        for (int i = 0; i < n; i++) v.add(new Product("VP" + i, "Item" + i, "Cat", 1.0, 1, "Supp"));

        System.gc();
        long after = rt.totalMemory() - rt.freeMemory();
        return Math.max(0, after - before);
    }

    private static long estimateArrayListMemory(int n) {
        Runtime rt = Runtime.getRuntime();
        System.gc();
        long before = rt.totalMemory() - rt.freeMemory();

        ArrayList<Product> a = new ArrayList<>();
        for (int i = 0; i < n; i++) a.add(new Product("AP" + i, "Item" + i, "Cat", 1.0, 1, "Supp"));

        System.gc();
        long after = rt.totalMemory() - rt.freeMemory();
        return Math.max(0, after - before);
    }
}
