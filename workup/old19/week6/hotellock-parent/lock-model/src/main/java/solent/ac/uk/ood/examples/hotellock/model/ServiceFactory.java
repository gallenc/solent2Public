package solent.ac.uk.ood.examples.hotellock.model;

public interface ServiceFactory {

    public HotelRoomLockService getHotelRoomLockService();

    public HotelReceptionService getHotelReceptionService();
}
