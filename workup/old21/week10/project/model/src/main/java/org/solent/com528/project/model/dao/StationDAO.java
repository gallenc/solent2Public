package org.solent.com528.project.model.dao;

import java.util.List;
import java.util.Set;
import org.solent.com528.project.model.dto.Station;

public interface StationDAO {

    public Station findById(Long id);

    public Station findByName(String name);

    public Station save(Station station);

    public void deleteById(Long id);

    public void deleteAll();

    public void delete(Station station);

    public List<Station> findAll();

    public List<Station> findByZone(Integer zone);
    
    public void saveAll(List<Station> stationList);
    
    public Set<Integer> getAllZones();
    
}
