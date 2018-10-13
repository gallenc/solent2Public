package solent.ac.uk.ood.examples.cardvalidator.model;

/**
 * CreditCard contains key fields of a credit card a six-digit[2] Issuer Identification Number (IIN), [a] the first digit of which is the major industry
 * identifier (MII) a variable length (up to 12 digits) individual account identifier a single check digit calculated using the Luhn algorithm[4]
 */
public class CreditCard {

    // used to check only numbers 
    private String checkNumericRegex = "[0-9]+";

    // used to select IIN
    private static final int IIN_SELECTOR = 6;

    private String name;

    private String endDate;

    private String cardnumber;

    private String cvv;

    private String issueNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        if (endDate == null || endDate.length() != 4 || !endDate.matches(checkNumericRegex)) {
            throw new IllegalArgumentException("endDate must be 4 numeric characters");
        }
        Integer mm = Integer.parseInt(endDate.substring(0, 2));
        if (mm < 1 || mm > 12) {
            throw new IllegalArgumentException("month must be between 01 and 12 not " + mm);
        }
        this.endDate = endDate;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        // note visa 16, amex 15 characters
        if (cardnumber == null || cardnumber.length() < 15 || !cardnumber.matches(checkNumericRegex)) {
            throw new IllegalArgumentException("cardNumber must be atleast 15 numeric characters " + cardnumber);
        }
        this.cardnumber = cardnumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        if (cvv == null || cvv.length() < 3 || cvv.length() > 4 || !cvv.matches(checkNumericRegex)) {
            throw new IllegalArgumentException("cvv must be 3 or 4 numeric characters " + cvv);
        }
        this.cvv = cvv;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        if (issueNumber == null || issueNumber.length() != 2 || !issueNumber.matches(checkNumericRegex)) {
            throw new IllegalArgumentException("issueNumber must be 2 numeric characters " + issueNumber);
        }
        this.issueNumber = issueNumber;
    }

    // generated fields
    public String getIssuerIdentificationNumber() {
        return cardnumber.substring(0, IIN_SELECTOR);
    }

    public String  getIndividualAccountIdentifier(){
        return cardnumber.substring(IIN_SELECTOR, cardnumber.length() - 1);
    }

    // override to string to make debuggine easier
    @Override
    public String toString() {
        return "CreditCard{" 
                + "name=" + name 
                + ", endDate=" + endDate 
                + ", cardnumber=" + cardnumber 
                + ", cvv=" + cvv 
                + ", issueNumber=" + issueNumber 
                + ", issuerIdentificationNumber=" + getIssuerIdentificationNumber() 
                + ", individualAccountIdentifier()=" + getIndividualAccountIdentifier() 
                + '}';
    }

}
