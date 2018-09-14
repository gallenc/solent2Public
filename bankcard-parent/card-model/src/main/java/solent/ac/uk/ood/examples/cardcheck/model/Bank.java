package solent.ac.uk.ood.examples.cardcheck.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bank {

    private String name;

    private String sortcode;

    private String bincode;

    private Integer id;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sortcode
     */
    public String getSortcode() {
        return sortcode;
    }

    /**
     * @param sortcode the sortcode to set
     */
    public void setSortcode(String sortcode) {
        this.sortcode = sortcode;
    }

    /**
     * @return the bincode
     */
    public String getBincode() {
        return bincode;
    }

    /**
     * @param bincode the bincode to set
     */
    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
