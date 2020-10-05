
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.meter.model.dto.test;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.solent.com504.meter.model.dto.ChargeBand;
import org.solent.com504.meter.model.dto.DailyChargingScheme;
import org.solent.com504.meter.model.dto.DayOfWeek;
import org.solent.com504.meter.model.dto.Location;

import org.solent.com504.meter.model.dto.MeterReplyMessage;
import org.solent.com504.meter.model.dto.ParkingMeter;
import org.solent.com504.meter.model.dto.WeeklyChargingScheme;

public class ModelMeterJaxbTest {

    @Test
    public void testTransactionJaxb() {

        try {

            // test file we will write and read. 
            // Note in target folder so that will be deleted on each run and will not be saved to git
            File file = new File("target/testTransactionData.xml");
            System.out.println("writing test file to " + file.getAbsolutePath());

            // this contains a list of Jaxb annotated classes for the context to parse
            // NOTE you must also have a jaxb.index or ObjectFactory in the same classpath
            JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com504.meter.model.dto");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            MeterReplyMessage meterReplyMessage = new MeterReplyMessage();

            String carParkName = "car park 1";

            meterReplyMessage.setDebugMessage("no debug message");
            meterReplyMessage.setCode(200);

            ParkingMeter parkingMeterConfig = new ParkingMeter();
            meterReplyMessage.setParkingMeterConfig(parkingMeterConfig);
            Location location = new Location();
            location.setAddress("10 downing Stret SW1");
            location.setCarParkName("Prime Mininser Park");
            //51.503497, -0.127577
            location.setLatitude(51.503497);
            location.setLongitude(-0.127577);
            parkingMeterConfig.setLocation(location);
            parkingMeterConfig.setSerialNumber(UUID.randomUUID().toString());

            WeeklyChargingScheme weeklyChargingScheme = new WeeklyChargingScheme();
            parkingMeterConfig.setWeeklyChargingScheme(weeklyChargingScheme);

            List<DailyChargingScheme> dailyChargingSchemeList = new ArrayList<DailyChargingScheme>();
            weeklyChargingScheme.setDailyChargingSchemeList(dailyChargingSchemeList);

            // set up for monday
            DailyChargingScheme dailyChargingScheme1 = new DailyChargingScheme();
            dailyChargingScheme1.setDay(DayOfWeek.MONDAY);
            List<ChargeBand> chargeBandList1 = new ArrayList<ChargeBand>();
            ChargeBand band1_1 = new ChargeBand();
            band1_1.setHrs(12);
            band1_1.setMins(59);
            band1_1.setPricePerHr(1.15);
            chargeBandList1.add(band1_1);
            ChargeBand band1_2 = new ChargeBand();
            band1_2.setHrs(15);
            band1_2.setMins(59);
            band1_2.setPricePerHr(2.0);
            chargeBandList1.add(band1_2);
            ChargeBand band1_3 = new ChargeBand();
            band1_3.setHrs(23);
            band1_3.setMins(59);
            band1_3.setPricePerHr(3.0);
            chargeBandList1.add(band1_3);
            dailyChargingScheme1.setChargeBandList(chargeBandList1);
            dailyChargingSchemeList.add(dailyChargingScheme1);

            // set up for tuesday
            DailyChargingScheme dailyChargingScheme2 = new DailyChargingScheme();
            dailyChargingScheme2.setDay(DayOfWeek.TUESDAY);
            ChargeBand band2_1 = new ChargeBand();
            band2_1.setHrs(11);
            band2_1.setMins(00);
            band2_1.setPricePerHr(1.15);
            List<ChargeBand> chargeBandList2 = new ArrayList<ChargeBand>();
            chargeBandList2.add(band2_1);
            dailyChargingScheme2.setChargeBandList(chargeBandList2);
            dailyChargingSchemeList.add(dailyChargingScheme2);

            // create XML from the object
            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(meterReplyMessage, System.out);
            jaxbMarshaller.marshal(meterReplyMessage, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(meterReplyMessage, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            MeterReplyMessage receivedTransactionResult = (MeterReplyMessage) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedTransactionResult, sw2);

            // check transmitted and recieved messages are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }
}
