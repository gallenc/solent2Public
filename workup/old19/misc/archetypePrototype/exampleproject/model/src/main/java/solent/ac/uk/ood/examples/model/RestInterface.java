package solent.ac.uk.ood.examples.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingEntites(Thing thingTempate);
    
    public ReplyMessage retrieveThing(Integer id);
    
}
