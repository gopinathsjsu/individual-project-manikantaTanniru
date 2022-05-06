
package inventory;

public class Inventory {
    private String category;
    private String itemName;
    private int quantity;
    private double price;

    /**
     * Constructor
     * @param category
     * @param itemName
     * @param quantity
     * @param price 
     */
    public Inventory(String category, String itemName, int quantity, double price) {
        this.category = category;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Inventory{" + "category=" + category + ", item=" + itemName + ", quantity=" + quantity + ", price=" + price + '}';
    }
}
