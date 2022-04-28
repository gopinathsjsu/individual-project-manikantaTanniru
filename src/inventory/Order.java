
package inventory;

public class Order {
    // instance variables
    private String itemName;
    private int quantity;
    private String cardNumber;

    /**
     * Constructor
     * @param itemName
     * @param quantity
     * @param cardNumber 
     */
    public Order(String itemName, int quantity, String cardNumber) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.cardNumber = cardNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return  itemName + ", " + quantity + ", " + cardNumber;
    }
       
}

class Receipt {
    // instance variable
    private double amountPaid;

    public Receipt(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

}

