package inventory;

public class CardDetails {

    private String cardNumber;

    /**
     * Constructor
     *
     * @param cardNumber
     */
    public CardDetails(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return cardNumber;
    }

}
