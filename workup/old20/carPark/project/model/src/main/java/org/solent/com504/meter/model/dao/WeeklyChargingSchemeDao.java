package org.solent.com504.meter.model.dao;

import java.util.List;
import org.solent.com504.meter.model.dto.WeeklyChargingScheme;

public interface WeeklyChargingSchemeDao {

    public WeeklyChargingScheme  findById(Long id);

    public WeeklyChargingScheme save(WeeklyChargingScheme weeklyChargingScheme);

    public List<WeeklyChargingScheme> findAll();

    public void deleteById(Long id);

    public void deleteAll();

    public WeeklyChargingScheme findBychargingSchemeName(String chargingSchemeName);
}
