package solent.ac.uk.ood.examples.hotellock.model;

import java.util.Date;

public interface HotelReceptionService {

    public String createCardCode(String roomNumber, Date startDate, Date endDate);

    public CardKey readCard(String cardCode);

    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider);
}
