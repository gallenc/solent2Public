#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingEntites(Entity entityTempate);
    
    public ReplyMessage retrieveEntity(Integer id);
    
}
