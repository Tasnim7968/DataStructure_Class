import java.util.Vector;

public class Order {
    private String orderId;
    private String customerName;
    private String orderDate;   // "YYYY-MM-DD"
    private Vector<OrderItem> items;
    private String orderStatus; // Pending, Processing, Shipped, Delivered, Cancelled

    public Order(String orderId, String customerName, String orderDate) {
        if (orderId == null || orderId.trim().isEmpty()) throw new IllegalArgumentException("orderId required");
        if (customerName == null || customerName.trim().isEmpty()) throw new IllegalArgumentException("customerName required");
        if (orderDate == null || orderDate.trim().isEmpty()) throw new IllegalArgumentException("orderDate required");

        this.orderId = orderId.trim();
        this.customerName = customerName.trim();
        this.orderDate = orderDate.trim();
        this.items = new Vector<>();
        this.orderStatus = "Pending";
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getOrderDate() { return orderDate; }
    public String getOrderStatus() { return orderStatus; }

    public void addItem(OrderItem item) {
        if (item == null) {
            System.out.println("[Error] Cannot add null OrderItem.");
            return;
        }

        // If product already in order, merge quantities
        OrderItem existing = findItem(item.getProductId());
        if (existing != null) {
            int newQty = existing.getQuantity() + item.getQuantity();
            existing.setQuantity(newQty);
            existing.calculateSubtotal();
            return;
        }

        items.add(item);
    }

    public boolean removeItem(String productId) {
        OrderItem item = findItem(productId);
        if (item == null) return false;
        return items.remove(item);
    }

    public OrderItem findItem(String productId) {
        if (productId == null) return null;
        String id = productId.trim();
        for (int i = 0; i < items.size(); i++) {
            OrderItem it = items.get(i);
            if (it.getProductId().equals(id)) return it;
        }
        return null;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).calculateSubtotal();
        }
        return total;
    }

    public int getTotalItems() {
        int totalQty = 0;
        for (int i = 0; i < items.size(); i++) {
            totalQty += items.get(i).getQuantity();
        }
        return totalQty;
    }

    public void updateStatus(String newStatus) {
        if (newStatus == null) {
            System.out.println("[Error] Status cannot be null.");
            return;
        }
        String s = newStatus.trim();
        if (!isValidStatus(s)) {
            System.out.println("[Error] Invalid status: " + s);
            return;
        }
        this.orderStatus = s;
    }

    private boolean isValidStatus(String s) {
        return s.equals("Pending") || s.equals("Processing") || s.equals("Shipped")
                || s.equals("Delivered") || s.equals("Cancelled");
    }

    public void printOrder() {
        System.out.println("====================================================");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Date: " + orderDate);
        System.out.println("Status: " + orderStatus);
        System.out.println("----------------------------------------------------");
        if (items.isEmpty()) {
            System.out.println("(No items)");
        } else {
            System.out.printf("%-6s | %-18s | %-5s | %-10s | %-10s%n",
                    "PID", "Name", "Qty", "UnitPrice", "Subtotal");
            System.out.println("----------------------------------------------------");
            for (int i = 0; i < items.size(); i++) {
                OrderItem it = items.get(i);
                System.out.printf("%-6s | %-18s | %-5d | $%-9.2f | $%-9.2f%n",
                        it.getProductId(), trimTo(it.getProductName(), 18),
                        it.getQuantity(), it.getUnitPrice(), it.getSubtotal());
            }
        }
        System.out.println("----------------------------------------------------");
        System.out.printf("TOTAL: $%.2f (Total Qty: %d)%n", calculateTotal(), getTotalItems());
        System.out.println("====================================================");
    }

    private String trimTo(String s, int max) {
        if (s == null) return "";
        if (s.length() <= max) return s;
        return s.substring(0, Math.max(0, max - 1)) + "…";
    }

    public Vector<OrderItem> getItems() {
        return new Vector<>(items); // return copy
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', customer='%s', date='%s', status='%s', items=%d, total=%.2f}",
                orderId, customerName, orderDate, orderStatus, items.size(), calculateTotal());
    }
}
