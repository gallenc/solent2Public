/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.service.rest;

import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.HotelRoomLockService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.model.ServiceFactory;
import solent.ac.uk.ood.examples.hotellock.reception.HotelReceptionServiceImpl;
import solent.ac.uk.ood.examples.hotellock.roomlock.HotelRoomLockServiceImpl;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;

/**
 * The service factory creates and only returns one instance of the service implementations Note that it is synchronized so that separate threads can ask for
 * the service
 *
 * @author cgallen
 */
public class ServiceFactoryImpl implements ServiceFactory {

    private static HotelRoomLockService hotelRoomLockService = null;

    private static HotelReceptionService hotelReceptionService = null;

    @Override
    public HotelRoomLockService getHotelRoomLockService() {
        if (hotelRoomLockService == null) {
            synchronized (this) {
                if (hotelRoomLockService == null) {
                    hotelRoomLockService = new HotelRoomLockServiceImpl();
                    SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
                    hotelRoomLockService.setSecretKeyProvider(secretKeyProvider);
                }
            }
        }
        return hotelRoomLockService;
    }

    @Override
    public HotelReceptionService getHotelReceptionService() {
        if (hotelReceptionService == null) {
            synchronized (this) {
                if (hotelReceptionService == null) {
                    hotelReceptionService = new HotelReceptionServiceImpl();
                    SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
                    hotelReceptionService.setSecretKeyProvider(secretKeyProvider);
                }
            }
        }
        return hotelReceptionService;
    }

}
