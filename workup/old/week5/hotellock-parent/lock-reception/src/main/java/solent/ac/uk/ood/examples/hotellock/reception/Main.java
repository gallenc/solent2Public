/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.reception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;

/**
 *
 * @author cgallen
 */
public class Main {

    public static void main(String[] args) {

        //set up hotel reception service
        HotelReceptionService hotelReceptionService = new HotelReceptionServiceImpl();
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        hotelReceptionService.setSecretKeyProvider(secretKeyProvider);

        // used to parse dates
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");

        // used to read lines from keyboard
        Scanner scan = new Scanner(System.in);

        boolean repeat = true;
        while (repeat == true) {
            
            Date now = new Date();

            // constants used to generate default values
            Date tomorrow = new Date(now.getTime() + 1000 * 60 * 60 * 24); // 1 day later
            String timeStrNow = df.format(now);
            String timeStrTomorrow = df.format(tomorrow.getTime());

            // variables for card
            String roomNumber = "";
            Date startDate = null;
            Date endDate = null;

            System.out.println("\nHotel Reception Service Command Line");
            System.out.println("------------------------------------");

            boolean correctInput = false;
            String selection = "";
            while (!correctInput) {
                System.out.println("enter generate card (1) or read card (2) [1]");;
                selection = scan.nextLine().trim();
                if (selection.isEmpty() || "1".equals(selection) || "2".equals(selection)) {
                    correctInput = true;
                }
                if (selection.isEmpty()) {
                    selection = "1";
                }
            }

            if ("2".equals(selection)) {
                //2.  read card
                correctInput = false;
                String cardCode = null;
                while (!correctInput) {
                    System.out.println("enter generated cardcode");
                    cardCode = scan.nextLine().trim();
                    if (!cardCode.isEmpty()) {
                        correctInput = true;
                    }
                }

                try {
                    CardKey cardkey = hotelReceptionService.readCard(cardCode);
                    System.out.println("card details: " + cardkey);
                } catch (Exception ex) {
                    System.out.println("could not parse cardcode :" + cardCode);
                }

            } else {
                // 1. write card

                System.out.println("enter card details [values in brackets are default values]");
                correctInput = false;

                // enter room number
                while (!correctInput) {
                    System.out.print("enter room number: [101a]");
                    roomNumber = scan.nextLine().trim();
                    if (roomNumber.isEmpty()) {
                        roomNumber = "101a";
                    }
                    correctInput = true;
                }

                // start date
                correctInput = false;
                String lineStr = "no entry";
                while (!correctInput) {
                    try {
                        System.out.println("enter start date and time dd/mm/yy hh:dd [" + timeStrNow + "]");
                        lineStr = scan.nextLine().trim();
                        if (lineStr.isEmpty()) {
                            lineStr = timeStrNow;
                        }
                        startDate = df.parse(lineStr);
                        correctInput = true;
                    } catch (Exception ex) {
                        correctInput = false;
                        System.out.println("error cannot parse input " + lineStr);
                    }
                }

                // end date
                correctInput = false;
                lineStr = "no entry";
                while (!correctInput) {
                    try {
                        System.out.println("enter end date and time dd/mm/yy hh:dd [" + timeStrTomorrow + "]");
                        lineStr = scan.nextLine().trim();
                        if (lineStr.isEmpty()) {
                            lineStr = timeStrTomorrow;
                        }
                        endDate = df.parse(lineStr);
                        correctInput = true;
                    } catch (Exception ex) {
                        correctInput = false;
                        System.out.println("error cannot parse input " + lineStr);
                    }
                }

                System.out.println("generating card from details: roomNumber:" + roomNumber
                        + " startDate: " + df.format(startDate)
                        + " endDate: " + df.format(endDate));

                // now generate and print out the cardCode
                String cardCode = hotelReceptionService.createCardCode(roomNumber, startDate, endDate);
                System.out.println("generated card code: " + cardCode);

                System.out.println("run again ? y/n [y]");
                lineStr = scan.nextLine().trim();

                repeat = false;
                // ask if do again
                if (lineStr.isEmpty() || lineStr.toLowerCase().equals("y")) {
                    repeat = true;
                }
            }
        }
        System.out.println("good bye !!");

    }
}
