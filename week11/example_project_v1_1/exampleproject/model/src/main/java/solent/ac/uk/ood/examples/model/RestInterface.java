package solent.ac.uk.ood.examples.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingEntites(Entity entityTempate);
    
    public ReplyMessage retrieveEntity(Integer id);
    
}
