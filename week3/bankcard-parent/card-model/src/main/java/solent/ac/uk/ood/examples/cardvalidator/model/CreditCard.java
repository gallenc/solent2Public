package solent.ac.uk.ood.examples.cardvalidator.model;

public class CreditCard {
    
    // used to check only numbers 
    private String checkNumericRegex = "[0-9]+"; 
    
//    a six-digit[2] Issuer Identification Number (IIN),
//    [a] the first digit of which is the major industry identifier (MII)
//    a variable length (up to 12 digits) individual account identifier
//    a single check digit calculated using the Luhn algorithm[4]
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
        if(endDate.length()!=4 || ! endDate.matches(checkNumericRegex)) 
            throw new IllegalArgumentException("endDate must be 4 numeric characters");
        this.endDate = endDate;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        // note visa 16, amex 15 characters
        if(cardnumber.length()<15 || ! endDate.matches(checkNumericRegex)) 
            throw new IllegalArgumentException("cardNumber must be atleast 15 numeric characters");
        this.cardnumber = cardnumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        if(cvv.length() <3 || cvv.length()>4 || ! endDate.matches(checkNumericRegex)) 
            throw new IllegalArgumentException("cvv must be 3 or 4 numeric characters"); 
        this.cvv = cvv;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        if(issueNumber.length()!=2|| ! endDate.matches(checkNumericRegex)) 
            throw new IllegalArgumentException("issueNumber must be 2 numeric characters"); 
        this.issueNumber = issueNumber;
    }

    // generated fields
    public String getIndividualAccountIdentifier() {
        return cardnumber.substring(0, IIN_SELECTOR-1);
    }

    public String getIssuerIdentificationNumber() {
        return cardnumber.substring(IIN_SELECTOR-1, cardnumber.length()-2);
    }
}
