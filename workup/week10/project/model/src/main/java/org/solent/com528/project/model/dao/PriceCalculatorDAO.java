package org.solent.com528.project.model.dao;

import java.util.Date;
import org.solent.com528.project.model.dto.PriceBand;
import org.solent.com528.project.model.dto.PricingDetails;

public interface PriceCalculatorDAO {

    public PricingDetails getPricingDetails();

    public Double getPricePerZone(Date startTime);

    public PricingDetails savePricingDetails(PricingDetails pricingList);

    public Date getBand(Date startTime);

    public void addPriceBand(PriceBand priceBand);

    public void deletePriceBand(PriceBand priceBand);
}
