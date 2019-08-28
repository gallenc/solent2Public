package solent.ac.uk.ood.examples.hotellock.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestMessage {

    private CardKey cardkey = null;

    private Boolean unlocked = null;

    private String cardCode = null;

    private String errorMessage = null;

    /**
     * @return the cardkey
     */
    public CardKey getCardkey() {
        return cardkey;
    }

    /**
     * @param cardkey the cardkey to set
     */
    public void setCardkey(CardKey cardkey) {
        this.cardkey = cardkey;
    }

    /**
     * @return the unlocked
     */
    public Boolean getUnlocked() {
        return unlocked;
    }

    /**
     * @param unlocked the unlocked to set
     */
    public void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }

    /**
     * @return the cardCode
     */
    public String getCardCode() {
        return cardCode;
    }

    /**
     * @param cardCode the cardCode to set
     */
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
