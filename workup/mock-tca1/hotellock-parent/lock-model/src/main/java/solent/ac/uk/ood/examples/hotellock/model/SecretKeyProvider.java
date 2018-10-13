package solent.ac.uk.ood.examples.hotellock.model;

public interface SecretKeyProvider {

    public String encodeCard(CardKey card);

    public CardKey decodeCard(String cardString);
}
