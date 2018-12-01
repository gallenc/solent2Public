package solent.ac.uk.ood.examples.exampleproject.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingEntites(Entity entityTempate);
    
    public ReplyMessage retrieveEntity(Integer id);
    
}
