package solent.ac.uk.ood.examples.hotellock.model;

public interface HotelRoomLockService {

    public boolean unlockDoor(String cardCode);

    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider);

    public void setRoomNumber(String roomNumber);
}
