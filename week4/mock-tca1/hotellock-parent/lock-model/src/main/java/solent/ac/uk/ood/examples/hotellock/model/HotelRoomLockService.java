package solent.ac.uk.ood.examples.hotellock.model;

public interface HotelRoomLockService {

    public String unlockDoor(String cardCode);

    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider);

    public String setRoomNumber(String roomNumber);
}
