#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReplyMessage {

    private Integer code;

    private String debugMessage;
    
    private EntityList entityList=new EntityList();


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public EntityList getEntityList() {
        return entityList;
    }

    public void setEntityList(EntityList entityList) {
        this.entityList = entityList;
    }

    @Override
    public String toString() {
        return "ReplyMessage{" + "code=" + code + ", debugMessage=" + debugMessage + ", entityList=" + entityList + '}';
    }



}
