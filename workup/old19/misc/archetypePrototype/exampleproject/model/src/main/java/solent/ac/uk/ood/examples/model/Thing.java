package solent.ac.uk.ood.examples.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple example entity with 3 fields
 *
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Thing {

    private Integer id;

    private String field_A = null;

    private String field_B = null;

    private String field_C = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField_A() {
        return field_A;
    }

    public void setField_A(String field_A) {
        this.field_A = field_A;
    }

    public String getField_B() {
        return field_B;
    }

    public void setField_B(String field_B) {
        this.field_B = field_B;
    }

    public String getField_C() {
        return field_C;
    }

    public void setField_C(String field_C) {
        this.field_C = field_C;
    }

    @Override
    public String toString() {
        return "Thing{" + "id=" + id
                + ", field_A=" + field_A
                + ", field_B=" + field_B
                + ", field_C=" + field_C + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Thing other = (Thing) obj;
        if (!Objects.equals(this.field_A, other.field_A)) {
            return false;
        }
        if (!Objects.equals(this.field_B, other.field_B)) {
            return false;
        }
        if (!Objects.equals(this.field_C, other.field_C)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
