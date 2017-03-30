package webconsole.co.in.cardless;

/**
 * Created by Harsh Mathur on 30-03-2017.
 */

public class CardInformation {
    private String name ;
    private String cvv ;
    private String expiry ;
    private String cardNumber ;

    public CardInformation() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
