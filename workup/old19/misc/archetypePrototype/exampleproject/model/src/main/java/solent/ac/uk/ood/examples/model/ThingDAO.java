package solent.ac.uk.ood.examples.model;

import java.util.List;

public interface ThingDAO {

    public Thing createThing(Thing thing);

    public boolean deleteThing(Integer id);

    public void deleteAllThings();

    public Thing retrieveThing(Integer id);

    public List<Thing> retrieveAllThings();

    public List<Thing> retrieveMatchingThings(Thing thingTempate);

    public Thing updateThing(Thing thing);
}
