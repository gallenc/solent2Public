/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.roomlock;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solent.ac.uk.ood.examples.hotellock.model.HotelRoomLockService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;

/**
 *
 * @author cgallen
 */
public class Main {

    public static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        String roomNumber = "UNSET_ROOM_NUMBER"; // default
        
        // read properties to get room number
        Properties props = readProperties();
        if (props!=null && props.containsKey("org.hotellock.roomnumber")){
            roomNumber = (String) props.get("org.hotellock.roomnumber");
            LOG.debug("using room number from properties file "+roomNumber);
        }
   
        //set up hotel lock
        HotelRoomLockService hotelRoomLockService = new HotelRoomLockServiceImpl();
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        hotelRoomLockService.setSecretKeyProvider(secretKeyProvider);
        hotelRoomLockService.setRoomNumber(roomNumber);

        // used to read lines from keyboard
        Scanner scan = new Scanner(System.in);

        boolean repeat = true;
        while (repeat == true) {

            Date startDate = null;
            Date endDate = null;

            System.out.println("\nHotel Room " + roomNumber + " Lock Service Command Line");
            System.out.println("----------------------------------------------");

            boolean correctInput = false;
            String cardCode = null;
            while (!correctInput) {
                System.out.print("enter card key code: ");
                cardCode = scan.nextLine().trim();
                if (!cardCode.isEmpty()) {
                    correctInput = true;
                }
            }

            boolean roomUnlocked = false;
            try {
                roomUnlocked = hotelRoomLockService.unlockDoor(cardCode);
            } catch (Exception ex) {
                LOG.error("could not parse cardcode :" + cardCode, ex);
            }

            if (roomUnlocked) {
                System.out.println("SUCCESS ROOM UNLOCKED !!!");
            } else {
                System.out.println("SORRY ROOM STILL LOCKED !!!");
            }
            System.out.println("\nrun again ? y/n [y]");
            String lineStr = scan.nextLine().trim();

            repeat = false;
            // ask if do again
            if (lineStr.isEmpty() || lineStr.toLowerCase().equals("y")) {
                repeat = true;
            }
        }
        System.out.println("good bye !!");
    }

    public static Properties readProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            input = Main.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find properties file " + filename);
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);
            return prop;

        } catch (IOException ex) {
            LOG.error("could not load properties from file: ", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return null;

    }

}
