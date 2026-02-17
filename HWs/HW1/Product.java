import java.util.Objects;

public class Product implements Comparable<Product> {
    private String productId;
    private String name;
    private String category;
    private double price;
    private int quantityInStock;
    private String supplier;

    public Product(String productId, String name, String category, double price, int quantityInStock, String supplier) {
        setProductId(productId);
        setName(name);
        setCategory(category);
        setPrice(price);
        setQuantityInStock(quantityInStock);
        setSupplier(supplier);
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("productId cannot be null/empty");
        }
        this.productId = productId.trim();
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null/empty");
        }
        this.name = name.trim();
    }

    public String getCategory() { return category; }
    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("category cannot be null/empty");
        }
        this.category = category.trim();
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("price cannot be negative");
        this.price = price;
    }

    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock < 0) throw new IllegalArgumentException("quantityInStock cannot be negative");
        this.quantityInStock = quantityInStock;
    }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) {
        if (supplier == null || supplier.trim().isEmpty()) {
            throw new IllegalArgumentException("supplier cannot be null/empty");
        }
        this.supplier = supplier.trim();
    }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', category='%s', price=%.2f, qty=%d, supplier='%s'}",
                productId, name, category, price, quantityInStock, supplier);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return Objects.equals(this.productId, other.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public int compareTo(Product other) {
        if (other == null) return 1;
        return this.productId.compareTo(other.productId);
    }
}
