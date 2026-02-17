import java.util.Enumeration;
import java.util.Vector;

public class ProductInventory {
    private final Vector<Product> products;

    public ProductInventory() {
        this.products = new Vector<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("[Error] Cannot add null product.");
            return;
        }
        if (findProduct(product.getProductId()) != null) {
            System.out.println("[Warning] Duplicate productId found. Not added: " + product.getProductId());
            return;
        }
        products.add(product);
    }

    public boolean removeProduct(String productId) {
        Product p = findProduct(productId);
        if (p == null) return false;
        return products.remove(p);
    }

    public Product findProduct(String productId) {
        if (productId == null) return null;
        String id = productId.trim();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getProductId().equals(id)) return p;
        }
        return null;
    }

    public Vector<Product> getProductsByCategory(String category) {
        Vector<Product> result = new Vector<>();
        if (category == null) return result;
        String cat = category.trim();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getCategory().equalsIgnoreCase(cat)) result.add(p);
        }
        return result;
    }

    public Vector<Product> getLowStockProducts(int threshold) {
        Vector<Product> result = new Vector<>();
        if (threshold < 0) threshold = 0;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getQuantityInStock() < threshold) result.add(p);
        }
        return result;
    }

    public double getTotalInventoryValue() {
        double total = 0.0;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            total += p.getPrice() * p.getQuantityInStock();
        }
        return total;
    }

    public void updateStock(String productId, int quantityChange) {
        Product p = findProduct(productId);
        if (p == null) {
            System.out.println("[Error] Product not found: " + productId);
            return;
        }
        int newQty = p.getQuantityInStock() + quantityChange;
        if (newQty < 0) {
            System.out.println("[Error] Stock update would make quantity negative. Update rejected.");
            return;
        }
        p.setQuantityInStock(newQty);
    }

    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("(Inventory is empty)");
            return;
        }

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%-6s | %-18s | %-12s | %-10s | %-6s | %-12s%n",
                "ID", "Name", "Category", "Price", "Qty", "Supplier");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%-6s | %-18s | %-12s | $%-9.2f | %-6d | %-12s%n",
                    p.getProductId(), trimTo(p.getName(), 18), trimTo(p.getCategory(), 12),
                    p.getPrice(), p.getQuantityInStock(), trimTo(p.getSupplier(), 12));
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private String trimTo(String s, int max) {
        if (s == null) return "";
        if (s.length() <= max) return s;
        return s.substring(0, Math.max(0, max - 1)) + "…";
    }

    public int getTotalProducts() {
        return products.size();
    }

    public void printCapacityInfo() {
        System.out.println("Vector size: " + products.size());
        System.out.println("Vector capacity: " + products.capacity());
    }

    public void optimizeCapacity() {
        products.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < 0) minCapacity = 0;
        products.ensureCapacity(minCapacity);
    }

    public void printCapacityReport() {
        int size = products.size();
        int cap = products.capacity();
        double utilization = (cap == 0) ? 0.0 : (size * 100.0 / cap);
        int remainingBeforeResize = cap - size;

        System.out.println("=== Capacity Report ===");
        System.out.println("Current size: " + size);
        System.out.println("Current capacity: " + cap);
        System.out.printf("Utilization: %.2f%%%n", utilization);
        System.out.println("Elements before resize (approx): " + remainingBeforeResize);
    }

    public void printProductsUsingEnumeration() {
        System.out.println("=== Products (Enumeration) ===");
        Enumeration<Product> e = products.elements();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }

    }

    public Vector<Product> getAllProductsCopy() {
        return new Vector<>(products);
    }
}
