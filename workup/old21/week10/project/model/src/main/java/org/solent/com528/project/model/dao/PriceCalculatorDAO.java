package org.solent.com528.project.model.dao;

import java.util.Date;
import org.solent.com528.project.model.dto.Rate;
import org.solent.com528.project.model.dto.PriceBand;
import org.solent.com528.project.model.dto.PricingDetails;

public interface PriceCalculatorDAO {

    public PricingDetails getPricingDetails();

    public Double getPricePerZone(Date startTime);

    public PricingDetails savePricingDetails(PricingDetails pricingDetails);

    public Rate getRate(Date startTime);

    public void addPriceBand(PriceBand priceBand);

    public void deletePriceBand(PriceBand priceBand);
    
    public void deleteAll();
    
    public Double getOffpeakPricePerZone();

    public void setOffpeakPricePerZone(Double offpeakPricePerZone);

    public Double getPeakPricePerZone();

    public void setPeakPricePerZone(Double peakPricePerZone);
    
}
