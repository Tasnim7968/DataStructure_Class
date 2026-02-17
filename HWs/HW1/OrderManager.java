import java.util.Vector;

public class OrderManager {
    private final Vector<Order> orders;

    public OrderManager() {
        this.orders = new Vector<>();
    }

    public void addOrder(Order order) {
        if (order == null) {
            System.out.println("[Error] Cannot add null order.");
            return;
        }
        orders.add(order);
    }

    public Order findOrder(String orderId) {
        if (orderId == null) return null;
        String id = orderId.trim();
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.getOrderId().equals(id)) return o;
        }
        return null;
    }

    public Vector<Order> getOrdersByStatus(String status) {
        Vector<Order> result = new Vector<>();
        if (status == null) return result;
        String s = status.trim();
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.getOrderStatus().equalsIgnoreCase(s)) result.add(o);
        }
        return result;
    }

    public Vector<Order> getOrdersByCustomer(String customerName) {
        Vector<Order> result = new Vector<>();
        if (customerName == null) return result;
        String name = customerName.trim();
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.getCustomerName().equalsIgnoreCase(name)) result.add(o);
        }
        return result;
    }

    public double getTotalRevenue() {
        double revenue = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.getOrderStatus().equals("Delivered")) {
                revenue += o.calculateTotal();
            }
        }
        return revenue;
    }

    public void cancelOrder(String orderId) {
        Order o = findOrder(orderId);
        if (o == null) {
            System.out.println("[Error] Order not found: " + orderId);
            return;
        }
        o.updateStatus("Cancelled");
    }

    public void printAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("(No orders)");
            return;
        }
        System.out.println("=== All Orders ===");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }

    public Vector<Order> getPendingOrders() {
        return getOrdersByStatus("Pending");
    }

    public int getOrderCount() {
        return orders.size();
    }
}
