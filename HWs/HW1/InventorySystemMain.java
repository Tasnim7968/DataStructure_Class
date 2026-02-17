import java.util.Scanner;
import java.util.Vector;

public class InventorySystemMain {

    public static void main(String[] args) {
        ProductInventory inventory = new ProductInventory();
        OrderManager orderManager = new OrderManager();

        // Sample products
        inventory.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp"));
        inventory.addProduct(new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc"));
        inventory.addProduct(new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp"));

        // Sample orders
        Order order1 = new Order("O001", "Alice", "2024-01-15");
        order1.addItem(new OrderItem("P001", "Laptop", 1, 999.99));
        order1.addItem(new OrderItem("P003", "Mouse", 2, 29.99));
        orderManager.addOrder(order1);

        Order order2 = new Order("O002", "Bob", "2024-01-16");
        order2.addItem(new OrderItem("P002", "T-Shirt", 3, 19.99));
        orderManager.addOrder(order2);

        // Demonstration (non-interactive)
        System.out.println("=== DEMO RUN ===");
        inventory.printAllProducts();
        inventory.printCapacityInfo();
        inventory.printCapacityReport();

        Vector<Product> electronics = inventory.getProductsByCategory("Electronics");
        System.out.println("Electronics count: " + electronics.size());

        Vector<Product> lowStock = inventory.getLowStockProducts(10);
        System.out.println("Low stock items (<10): " + lowStock.size());

        System.out.printf("Total inventory value: $%.2f%n", inventory.getTotalInventoryValue());

        System.out.println();
        orderManager.printAllOrders();
        System.out.printf("Total revenue (Delivered only): $%.2f%n", orderManager.getTotalRevenue());

        // Demonstrate Enumeration
        System.out.println();
        inventory.printProductsUsingEnumeration();

        // Demonstrate Generics
        System.out.println("\n=== Generics Demo ===");
        Vector<Integer> ints = new Vector<>();
        ints.add(10); ints.add(20); ints.add(30);
        System.out.println("Sum: " + VectorUtils.sumNumbers(ints));
        System.out.println("Average: " + VectorUtils.averageNumbers(ints));
        System.out.println("Max int: " + VectorUtils.findMax(ints));

        Vector<String> words = new Vector<>();
        words.add("apple"); words.add("zebra"); words.add("banana");
        System.out.println("Max string: " + VectorUtils.findMax(words));

        // swap demo
        VectorUtils.swap(words, 0, 2);
        System.out.println("After swap: " + words);

        // filter demo
        Vector<Product> filtered = VectorUtils.filter(inventory.getAllProductsCopy(),
                p -> p != null && p.getCategory().equalsIgnoreCase("Electronics"));
        System.out.println("Filtered Electronics (generic filter): " + filtered.size());

        // Optional menu (simple)
        runMenu(inventory, orderManager);
    }

    private static void runMenu(ProductInventory inventory, OrderManager orderManager) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Inventory System Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Find Product");
            System.out.println("4. List All Products");
            System.out.println("5. Create Order");
            System.out.println("6. View Orders");
            System.out.println("7. Process Order (Update Status)");
            System.out.println("8. Generate Reports");
            System.out.println("9. Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addProductMenu(sc, inventory);
                    break;
                case "2":
                    System.out.print("Enter productId to remove: ");
                    String rid = sc.nextLine().trim();
                    System.out.println(inventory.removeProduct(rid) ? "Removed." : "Not found.");
                    break;
                case "3":
                    System.out.print("Enter productId to find: ");
                    String fid = sc.nextLine().trim();
                    Product fp = inventory.findProduct(fid);
                    System.out.println(fp == null ? "Not found." : fp);
                    break;
                case "4":
                    inventory.printAllProducts();
                    break;
                case "5":
                    createOrderMenu(sc, orderManager);
                    break;
                case "6":
                    orderManager.printAllOrders();
                    break;
                case "7":
                    processOrderMenu(sc, orderManager);
                    break;
                case "8":
                    generateReports(inventory, orderManager);
                    break;
                case "9":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addProductMenu(Scanner sc, ProductInventory inventory) {
        try {
            System.out.print("Product ID: ");
            String id = sc.nextLine().trim();
            System.out.print("Name: ");
            String name = sc.nextLine().trim();
            System.out.print("Category: ");
            String cat = sc.nextLine().trim();
            System.out.print("Price: ");
            double price = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Quantity: ");
            int qty = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Supplier: ");
            String supp = sc.nextLine().trim();

            inventory.addProduct(new Product(id, name, cat, price, qty, supp));
            System.out.println("Added (if not duplicate).");
        } catch (Exception e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    private static void createOrderMenu(Scanner sc, OrderManager orderManager) {
        try {
            System.out.print("Order ID: ");
            String oid = sc.nextLine().trim();
            System.out.print("Customer Name: ");
            String cname = sc.nextLine().trim();
            System.out.print("Order Date (YYYY-MM-DD): ");
            String date = sc.nextLine().trim();

            Order order = new Order(oid, cname, date);

            while (true) {
                System.out.print("Add item? (y/n): ");
                String yn = sc.nextLine().trim().toLowerCase();
                if (!yn.equals("y")) break;

                System.out.print("Product ID: ");
                String pid = sc.nextLine().trim();
                System.out.print("Product Name: ");
                String pname = sc.nextLine().trim();
                System.out.print("Quantity: ");
                int qty = Integer.parseInt(sc.nextLine().trim());
                System.out.print("Unit Price: ");
                double price = Double.parseDouble(sc.nextLine().trim());

                order.addItem(new OrderItem(pid, pname, qty, price));
            }

            orderManager.addOrder(order);
            System.out.println("Order created.");
        } catch (Exception e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    private static void processOrderMenu(Scanner sc, OrderManager orderManager) {
        System.out.print("Enter orderId: ");
        String oid = sc.nextLine().trim();
        Order o = orderManager.findOrder(oid);
        if (o == null) {
            System.out.println("Order not found.");
            return;
        }
        System.out.print("New status (Pending/Processing/Shipped/Delivered/Cancelled): ");
        String st = sc.nextLine().trim();
        o.updateStatus(st);
        System.out.println("Updated: " + o);
    }

    private static void generateReports(ProductInventory inventory, OrderManager orderManager) {
        System.out.println("\n=== REPORTS ===");
        System.out.printf("Total products: %d%n", inventory.getTotalProducts());
        System.out.printf("Total inventory value: $%.2f%n", inventory.getTotalInventoryValue());
        inventory.printCapacityReport();
        System.out.printf("Total orders: %d%n", orderManager.getOrderCount());
        System.out.printf("Pending orders: %d%n", orderManager.getPendingOrders().size());
        System.out.printf("Revenue (Delivered only): $%.2f%n", orderManager.getTotalRevenue());
    }
}
