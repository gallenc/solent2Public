/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.dao.jaxb;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dto.Station;
import org.solent.com528.project.model.dto.StationsList;

/**
 * Only implemented the necessary methods
 *
 * @author cgallen
 */
public class StationDAOJaxbImpl implements StationDAO {

    private final static Logger LOG = LogManager.getLogger(StationDAOJaxbImpl.class);

    private TreeMap<String, Station> stationTreeMap = new TreeMap();

    private String stationsFileName;

    public StationDAOJaxbImpl(String filename) {
        this.stationsFileName = filename;
        load();
    }

    @Override
    public synchronized Station findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Station findByName(String name) {
        return stationTreeMap.get(name);
    }

    @Override
    public synchronized Station save(Station station) {
        stationTreeMap.put(station.getName(), station);
        save();
        return station;
    }

    @Override
    public synchronized void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void deleteAll() {
        stationTreeMap.clear();
        save();
    }

    @Override
    public synchronized void delete(Station station) {
        stationTreeMap.remove(station.getName());
        save();
    }

    @Override
    public synchronized List<Station> findAll() {
        return new ArrayList(stationTreeMap.values());
    }

    @Override
    public synchronized List<Station> findByZone(Integer zone) {
        List<Station> stationList = new ArrayList<Station>();
        for (Station station : stationTreeMap.values()) {
            if (zone.equals(station.getZone())) {
                stationList.add(station);
            }
        }
        return stationList;
    }

    @Override
    public synchronized void saveAll(List<Station> stationList) {
        for (Station station : stationList) {
            stationTreeMap.put(station.getName(), station);
        }
        save();
    }

    /**
     * returns a set of all zones in list of stations    
    */
    @Override
    public synchronized  Set<Integer> getAllZones() {
        List<Station> stationList = this.findAll();
        Set<Integer> zones = new TreeSet();
        for (Station st : stationList) {
            zones.add(st.getZone());
        }
        return zones;
    }

    private void load() {

        File file = new File(stationsFileName);
        LOG.debug("loading stationsFileName from " + file.getAbsolutePath());

        if (!file.exists()) {
            LOG.debug("stationsFileName does not exist - creating new file ");
            // make parent directories needed for this file
            //file.mkdirs();
            save();
        } else {
            try {
                // this contains a list of Jaxb annotated classes for the context to parse
                // NOTE you must also have a jaxb.index or ObjectFactory in the same classpath
                JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com528.project.model.dto");
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();

                StationsList stationList = (StationsList) jaxbUnMarshaller.unmarshal(file);
                stationTreeMap.clear();
                for (Station station : stationList.getStationList()) {
                    stationTreeMap.put(station.getName(), station);
                }

            } catch (JAXBException e) {
                throw new RuntimeException("problem testing jaxb marshalling", e);
            }
        }
    }

    private void save() {

        File file = new File(stationsFileName);
        LOG.debug("saving station list to " + file.getAbsolutePath());

        // first remove old file before writing new data
        if (file.exists()) {
            LOG.debug("deleting old file ");
            file.delete();
        }

        try {
            // create parent directories if needed
            File parent = new File(file.getParent());
            LOG.debug("parent file: "+parent.getAbsolutePath());
            if (!parent.exists()) {
                parent.mkdirs();
            }
            // this contains a list of Jaxb annotated classes for the context to parse
            // NOTE you must also have a jaxb.index or ObjectFactory in the same classpath
            JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com528.project.model.dto");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            List<Station> listStations = this.findAll();
            StationsList stationList = new StationsList();
            stationList.setStationList(listStations);

            // string writer is used to compare received object
            if (LOG.isDebugEnabled()) {
                LOG.debug("stationList to write to file: " + stationList);
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(stationList, sw1);
                LOG.debug("save writing stationList to file: " + sw1);
            }

            jaxbMarshaller.marshal(stationList, file);

        } catch (JAXBException e) {
            throw new RuntimeException("problem marshalling", e);
        }
    }

}
