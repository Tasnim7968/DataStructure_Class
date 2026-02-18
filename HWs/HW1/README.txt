Vector-Based Inventory Management System

Files:
1) Product.java
   - Represents a store product with id, name, category, price, stock quantity, supplier.
   - Implements equals/hashCode based on productId.
   - Implements Comparable<Product> (compares by productId) for generic findMax demo.

2) ProductInventory.java
   - Manages products using Vector<Product>.
   - Supports add/remove/find, category filter, low stock filter, inventory value, stock updates.
   - Prints formatted tables and Vector capacity info.
   - Includes capacity management: ensureCapacity, trimToSize (optimizeCapacity), capacity report.
   - Demonstrates Enumeration via elements().

3) OrderItem.java
   - Represents one line item in an order with subtotal calculation.

4) Order.java
   - Manages Vector<OrderItem>.
   - Add/remove/find items, total cost, total quantity, status updates, formatted printing.

5) OrderManager.java
   - Manages Vector<Order>.
   - Find orders, filter by status/customer, revenue from Delivered orders, cancel order, print all.

6) VectorUtils.java
   - Generic utilities: swap, findMax, countMatches, filter (with a simple Predicate interface).
   - Bounded generics: sumNumbers, averageNumbers.

7) GenericContainer.java
   - Generic wrapper around Vector<T> with convenience methods.

8) VectorComparisonDemo.java
   - Compares Vector vs ArrayList performance and approximate memory usage.

9) InventorySystemMain.java
   - Integrates everything:
     - Adds sample products and orders
     - Demonstrates Vector capacity management, Enumeration, and generic utilities
     - Includes a simple optional interactive menu.

10) ThreadSafetyDemo.java (BONUS)
   - Demonstrates concurrency behavior differences between Vector and ArrayList.

How to compile:
  javac *.java

How to run:
  java InventorySystemMain
  java VectorComparisonDemo
  java ThreadSafetyDemo

Assumptions:
- OrderDate is stored as a String "YYYY-MM-DD" (no strict date parsing required).
- Revenue includes only orders with status "Delivered".
- Product compareTo uses productId for simplicity.

What I learned:
- Vector is synchronized and has capacity methods (capacity, ensureCapacity, trimToSize).
- Enumeration is legacy; Iterator is preferred in modern code.
- Generics allow reusable, type-safe utilities and containers.
